package dev.srinivas.ProductService.mapper;


import dev.srinivas.ProductService.dto.*;
import dev.srinivas.ProductService.model.Product;

import java.util.List;

/**
 * Mapper class for converting between different product DTOs and entity objects.
 */
public class ProductMapper {

    /**
     * Converts a ProductRequestDTO to a FakeStoreProductRequestDTO.
     * @param productRequestDTO the product request DTO
     * @return the corresponding FakeStoreProductRequestDTO
     */
    public static FakeStoreProductRequestDTO productRequestToFakeStoreProductRequest(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());
        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        return fakeStoreProductRequestDTO;
    }

    /**
     * Converts a FakeStoreProductResponseDTO to a ProductResponseDTO.
     * @param fakeStoreProductResponseDTO the fake store product response DTO
     * @return the corresponding ProductResponseDTO
     */
    public static ProductResponseDTO fakeStoreProductResponseToProductResponse(FakeStoreProductResponseDTO fakeStoreProductResponseDTO) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());
        return productResponseDTO;
    }

    /**
     * Converts a list of Product entities to a ProductListResponseDTO.
     * @param products the list of product entities
     * @return the corresponding ProductListResponseDTO
     */
    public static ProductListResponseDTO convertProductListToProductListResponseDTO(List<Product> products) {
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for (Product p : products) {
            ProductResponseDTO productResponseDTO = convertProductToProductResponseDTO(p);
            productListResponseDTO.getProducts().add(productResponseDTO);
        }
        return productListResponseDTO;
    }

    /**
     * Converts a Product entity to a ProductResponseDTO.
     * @param product the product entity
     * @return the corresponding ProductResponseDTO
     */
    public static ProductResponseDTO convertProductToProductResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setCategory(product.getCategory().getCategoryName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setPrice(product.getPrice().getAmount());
        productResponseDTO.setImage(product.getImage());
        productResponseDTO.setTitle(product.getTitle());
        productResponseDTO.setId(product.getId());
        return productResponseDTO;
    }
}
