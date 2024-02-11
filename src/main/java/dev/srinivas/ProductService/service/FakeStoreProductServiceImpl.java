package dev.srinivas.ProductService.service;


import dev.srinivas.ProductService.client.FakeStoreAPIClient;
import dev.srinivas.ProductService.dto.*;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import dev.srinivas.ProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.srinivas.ProductService.mapper.ProductMapper.fakeStoreProductResponseToProductResponse;
import static dev.srinivas.ProductService.mapper.ProductMapper.productRequestToFakeStoreProductRequest;
import static dev.srinivas.ProductService.util.ProductUtils.isNull;


@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreAPIClient fakeStoreAPIClient;


    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreAPIClient fakeStoreAPIClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
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
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {

        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);
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
