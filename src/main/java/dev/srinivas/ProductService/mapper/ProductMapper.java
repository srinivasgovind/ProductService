package dev.srinivas.ProductService.mapper;


import dev.srinivas.ProductService.dto.*;
import dev.srinivas.ProductService.model.Product;

import java.util.List;

public class ProductMapper {

    public static FakeStoreProductRequestDTO productRequestToFakeStoreProductRequest(ProductRequestDTO productRequestDTO){
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());
        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        return fakeStoreProductRequestDTO;
    }

    public static ProductResponseDTO fakeStoreProductResponseToProductResponse(FakeStoreProductResponseDTO fakeStoreProductResponseDTO){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());

        return productResponseDTO;
    }

    public  static ProductListResponseDTO convertProductListToProductListResponseDTO(List<Product> products){
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for(Product p : products) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setCategory(p.getCategory().getCategoryName());
            productResponseDTO.setDescription(p.getDescription());
            productResponseDTO.setPrice(p.getPrice().getAmount());
            productResponseDTO.setImage(p.getImage());
            productResponseDTO.setTitle(p.getTitle());
            productResponseDTO.setId(p.getId());
            productListResponseDTO.getProducts().add(productResponseDTO);
        }
        return productListResponseDTO;
    }

    public static ProductResponseDTO convertProductToProductResponseDTO(Product product){
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
