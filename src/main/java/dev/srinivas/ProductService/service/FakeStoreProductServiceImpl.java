package dev.srinivas.ProductService.service;


import dev.srinivas.ProductService.client.FakeStoreAPIClient;
import dev.srinivas.ProductService.dto.*;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import dev.srinivas.ProductService.model.Product;
import dev.srinivas.ProductService.security.TokenValidator;
import org.apache.lucene.util.automaton.ByteRunAutomaton;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static dev.srinivas.ProductService.mapper.ProductMapper.fakeStoreProductResponseToProductResponse;
import static dev.srinivas.ProductService.mapper.ProductMapper.productRequestToFakeStoreProductRequest;
import static dev.srinivas.ProductService.util.ProductUtils.isNull;


/**
 * Implementation of the ProductService interface using the FakeStore API.
 */
@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreAPIClient fakeStoreAPIClient;
    private TokenValidator tokenValidator;
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Constructor for FakeStoreProductServiceImpl.
     * @param restTemplateBuilder the RestTemplate builder
     * @param fakeStoreAPIClient the FakeStore API client
     * @param tokenValidator the token validator
     * @param redisTemplate the Redis template
     */
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreAPIClient fakeStoreAPIClient, TokenValidator tokenValidator, RedisTemplate redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
        this.tokenValidator = tokenValidator;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Retrieves all products from the FakeStore API.
     * @return a ProductListResponseDTO containing all products
     */
    @Override
    public ProductListResponseDTO getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOs = fakeStoreAPIClient.getAllProducts();
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for (FakeStoreProductResponseDTO fakeStoreProductResponseDTO : fakeStoreProductResponseDTOs) {
            productListResponseDTO.getProducts().add(fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO));
        }
        return productListResponseDTO;
    }

    /**
     * Retrieves a product by its ID from the FakeStore API, with Redis caching.
     * @param authToken the authentication token
     * @param id the UUID of the product
     * @return a ProductResponseDTO containing the product details
     * @throws ProductNotFoundException if the product is not found
     */
    @Override
    public ProductResponseDTO getProductById(String authToken, UUID id) throws ProductNotFoundException {
        // Check Redis first for data
        FakeStoreProductResponseDTO fakeStoreProductResponseRedis = (FakeStoreProductResponseDTO) redisTemplate.opsForHash().get("PRODUCTS", id);
        if (fakeStoreProductResponseRedis != null) {
            return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseRedis);
        }
        // TODO hardcoded id field needs to be modified
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(1);
        // Cache Miss
        redisTemplate.opsForHash().put("PRODUCTS", id, fakeStoreProductResponseDTO);
        if (isNull(fakeStoreProductResponseDTO)) {
            throw new ProductNotFoundException("Product not found with id : " + id);
        }
        return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    /**
     * Creates a new product using the FakeStore API.
     * @param productRequestDTO the product request DTO
     * @return a ProductResponseDTO containing the created product details
     */
    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = productRequestToFakeStoreProductRequest(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);
        return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    /**
     * Deletes a product by its ID using the FakeStore API.
     * @param id the ID of the product to delete
     * @return true if the product was successfully deleted
     */
    @Override
    public boolean deleteProduct(int id) {
        fakeStoreAPIClient.deleteProduct(id);
        return true;
    }

    /**
     * Updates a product by its ID. Not implemented yet.
     * @param id the ID of the product to update
     * @param updatedProduct the updated product details
     * @return null (not implemented)
     */
    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        // TODO: Implement updateProduct method
        return null;
    }

    /**
     * Finds a product by its title. Not implemented yet.
     * @param title the title of the product to find
     * @return null (not implemented)
     */
    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        // TODO: Implement findProductByTitle method
        return null;
    }
}
