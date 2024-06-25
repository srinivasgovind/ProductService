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


@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreAPIClient fakeStoreAPIClient;

    private TokenValidator tokenValidator;

    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreAPIClient fakeStoreAPIClient, TokenValidator tokenValidator, RedisTemplate redisTemplate){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
        this.tokenValidator = tokenValidator;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOs = fakeStoreAPIClient.getAllProducts();
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for(FakeStoreProductResponseDTO fakeStoreProductResponseDTO : fakeStoreProductResponseDTOs){
            productListResponseDTO.getProducts().add(fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO));
        }
        return productListResponseDTO;

    }

    @Override
    public ProductResponseDTO getProductById(String authToken, UUID id) throws ProductNotFoundException {
        //check Redis First for data
        FakeStoreProductResponseDTO fakeStoreProductResponseRedis = (FakeStoreProductResponseDTO) redisTemplate.opsForHash().get("PRODUCTS", id);
        if(fakeStoreProductResponseRedis != null){
            return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseRedis);
        }
        //TODO hardcoded id field need to be modified
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(1);
        //Cache Miss
        redisTemplate.opsForHash().put("PRODUCTS", id, fakeStoreProductResponseDTO);
        if(isNull(fakeStoreProductResponseDTO)){
            throw new ProductNotFoundException("Product not found with id : " + id);
        }
        return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO);

    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = productRequestToFakeStoreProductRequest(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);
        return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO);

    }

    @Override
    public boolean deleteProduct(int id) {
        fakeStoreAPIClient.deleteProduct(id);
        return true;
    }

    //TODO
    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        return null;
    }
}
