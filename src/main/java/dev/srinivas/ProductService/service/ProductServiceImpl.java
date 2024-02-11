package dev.srinivas.ProductService.service;


import dev.srinivas.ProductService.dto.ProductListResponseDTO;
import dev.srinivas.ProductService.dto.ProductRequestDTO;
import dev.srinivas.ProductService.dto.ProductResponseDTO;
import dev.srinivas.ProductService.exception.InvalidTitleException;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import dev.srinivas.ProductService.mapper.ProductMapper;
import dev.srinivas.ProductService.model.Product;
import dev.srinivas.ProductService.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public ProductListResponseDTO getAllProducts() {
        List<Product> productList = productRepository.findAll();
        ProductListResponseDTO productListResponseDTO = ProductMapper.convertProductListToProductListResponseDTO(productList);
        return productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException {
        if(title == null || title.isEmpty()){
            throw new InvalidTitleException("title is invalid");
        }
        Product product = productRepository.findByTitle(title);
        if(product == null){
            throw new ProductNotFoundException("product with given title is not found");
        }
        ProductResponseDTO productResponseDTO = ProductMapper.convertProductToProductResponseDTO(product);
        return productResponseDTO;
    }
}
