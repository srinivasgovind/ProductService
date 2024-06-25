package dev.srinivas.ProductService.service;


import dev.srinivas.ProductService.dto.ProductListResponseDTO;
import dev.srinivas.ProductService.dto.ProductRequestDTO;
import dev.srinivas.ProductService.dto.ProductResponseDTO;
import dev.srinivas.ProductService.exception.InvalidTitleException;
import dev.srinivas.ProductService.exception.InvalidTokenException;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import dev.srinivas.ProductService.mapper.ProductMapper;
import dev.srinivas.ProductService.model.Category;
import dev.srinivas.ProductService.repository.OpenSearchProductElasticsearchRepository;
import dev.srinivas.ProductService.model.Price;
import dev.srinivas.ProductService.model.Product;
import dev.srinivas.ProductService.repository.CategoryRepository;
import dev.srinivas.ProductService.repository.PriceRepository;
import dev.srinivas.ProductService.repository.ProductJpaRepository;
import dev.srinivas.ProductService.security.JWTObject;
import dev.srinivas.ProductService.security.TokenValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductJpaRepository productJpaRepository;

    private CategoryRepository categoryRepository;

    private PriceRepository priceRepository;

    private TokenValidator tokenValidator;

    private OpenSearchProductElasticsearchRepository openSearchProductElasticsearchRepository;

    public ProductServiceImpl(ProductJpaRepository productJpaRepository, CategoryRepository categoryRepository, PriceRepository priceRepository, TokenValidator tokenValidator
                              , OpenSearchProductElasticsearchRepository openSearchProductElasticsearchRepository
    ){
        this.productJpaRepository = productJpaRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.tokenValidator = tokenValidator;
        this.openSearchProductElasticsearchRepository = openSearchProductElasticsearchRepository;
    }
    @Override
    public ProductListResponseDTO getAllProducts() {


        List<Product> productList = productJpaRepository.findAll();
        ProductListResponseDTO productListResponseDTO = ProductMapper.convertProductListToProductListResponseDTO(productList);
        return productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(String authToken, UUID id) {
        Optional<JWTObject> jwtObjectOptional = tokenValidator.validateToken(authToken);

        if(jwtObjectOptional.isEmpty()){
           //Invalid token
            throw new InvalidTokenException("Token is not valid");
        }

        JWTObject jwtObject = jwtObjectOptional.get();
        Long userId = jwtObject.getUserId();
        Optional<Product> product = productJpaRepository.findById(id);
        ProductResponseDTO productResponse = ProductMapper.convertProductToProductResponseDTO(product.get());
        return productResponse;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Price mockPrice = new Price();
        mockPrice.setAmount(productRequestDTO.getPrice());
        mockPrice.setCurrency("INR");
        mockPrice.setDiscount(0);
        priceRepository.save(mockPrice);


        Category mockCategory = new Category();
        mockCategory.setCategoryName(productRequestDTO.getCategory());
        categoryRepository.save(mockCategory);

        String testTitle = "testProductTitle";
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle(productRequestDTO.getTitle());
        mockProduct.setDescription(productRequestDTO.getDescription());
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        mockProduct.setImage(productRequestDTO.getImage());

        //Save the product in Products Table
        Product savedProduct = productJpaRepository.save(mockProduct);

        //Save the product in Elastic search
        openSearchProductElasticsearchRepository.save(savedProduct);

        // Convert saved Product entity to ProductResponseDTO
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(savedProduct.getId()); // Assuming Product has an ID field
        responseDTO.setTitle(savedProduct.getTitle());
        responseDTO.setPrice(savedProduct.getPrice().getAmount());
        responseDTO.setDescription(savedProduct.getDescription());
        responseDTO.setImage(savedProduct.getImage());
        return responseDTO;

    }

    @Override
    public boolean deleteProduct(int id) {
        //TODO int id need to be replaced with UUID
        if (productJpaRepository.existsById((UUID.fromString("9fa06bde-3787-4199-88a3-98d8d3ccbee7")))) {
            productJpaRepository.deleteById((UUID.fromString("9fa06bde-3787-4199-88a3-98d8d3ccbee7")));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        //TODO int id need to be replaced with UUID
        Optional<Product> existingProductOpt = productJpaRepository.findById(UUID.fromString("9fa06bde-3787-4199-88a3-98d8d3ccbee7"));
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setImage(updatedProduct.getImage());
            // Save the updated product
            Product savedProduct = productJpaRepository.save(existingProduct);
            // Update the Elasticsearch repository as well
            openSearchProductElasticsearchRepository.save(savedProduct);
            return savedProduct;
        } else {
            return null;
        }
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException {
        if(title == null || title.isEmpty()){
            throw new InvalidTitleException("title is invalid");
        }
        Product product = productJpaRepository.findByTitle(title);
        if(product == null){
            throw new ProductNotFoundException("product with given title is not found");
        }
        ProductResponseDTO productResponseDTO = ProductMapper.convertProductToProductResponseDTO(product);
        return productResponseDTO;
    }
}
