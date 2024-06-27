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

/**
 * Service implementation for managing products.
 * This class provides methods to perform CRUD operations on products,
 * including retrieving all products, retrieving a product by its ID,
 * creating a new product, deleting a product, updating a product,
 * and finding a product by its title. It interacts with various repositories
 * and clients to perform these operations.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    private final ProductJpaRepository productJpaRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;
    private final TokenValidator tokenValidator;
    private final OpenSearchProductElasticsearchRepository openSearchProductElasticsearchRepository;

    /**
     * Constructor for ProductServiceImpl.
     * @param productJpaRepository the Product JPA repository
     * @param categoryRepository the Category repository
     * @param priceRepository the Price repository
     * @param tokenValidator the token validator
     * @param openSearchProductElasticsearchRepository the OpenSearch Elasticsearch repository
     */
    public ProductServiceImpl(ProductJpaRepository productJpaRepository, CategoryRepository categoryRepository, PriceRepository priceRepository, TokenValidator tokenValidator, OpenSearchProductElasticsearchRepository openSearchProductElasticsearchRepository) {
        this.productJpaRepository = productJpaRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.tokenValidator = tokenValidator;
        this.openSearchProductElasticsearchRepository = openSearchProductElasticsearchRepository;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<Product> productList = productJpaRepository.findAll();
        return ProductMapper.convertProductListToProductListResponseDTO(productList);
    }

    @Override
    public ProductResponseDTO getProductById(String authToken, UUID id) throws ProductNotFoundException {
        Optional<JWTObject> jwtObjectOptional = tokenValidator.validateToken(authToken);

        if (jwtObjectOptional.isEmpty()) {
            throw new InvalidTokenException("Token is not valid");
        }

        JWTObject jwtObject = jwtObjectOptional.get();
        Long userId = jwtObject.getUserId();
        Optional<Product> product = productJpaRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return ProductMapper.convertProductToProductResponseDTO(product.get());
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

        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle(productRequestDTO.getTitle());
        mockProduct.setDescription(productRequestDTO.getDescription());
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        mockProduct.setImage(productRequestDTO.getImage());

        Product savedProduct = productJpaRepository.save(mockProduct);
        openSearchProductElasticsearchRepository.save(savedProduct);

        return ProductMapper.convertProductToProductResponseDTO(savedProduct);
    }

    @Override
    public boolean deleteProduct(int id) {
        // TODO: int id needs to be replaced with UUID
        UUID uuid = UUID.fromString("9fa06bde-3787-4199-88a3-98d8d3ccbee7");
        if (productJpaRepository.existsById(uuid)) {
            productJpaRepository.deleteById(uuid);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        // TODO: int id needs to be replaced with UUID
        UUID uuid = UUID.fromString("9fa06bde-3787-4199-88a3-98d8d3ccbee7");
        Optional<Product> existingProductOpt = productJpaRepository.findById(uuid);
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setImage(updatedProduct.getImage());
            Product savedProduct = productJpaRepository.save(existingProduct);
            openSearchProductElasticsearchRepository.save(savedProduct);
            return savedProduct;
        } else {
            return null;
        }
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException {
        if (title == null || title.isEmpty()) {
            throw new InvalidTitleException("Title is invalid");
        }
        Product product = productJpaRepository.findByTitle(title);
        if (product == null) {
            throw new ProductNotFoundException("Product with given title is not found");
        }
        return ProductMapper.convertProductToProductResponseDTO(product);
    }
}