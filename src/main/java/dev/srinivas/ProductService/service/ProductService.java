package dev.srinivas.ProductService.service;


import dev.srinivas.ProductService.dto.ProductListResponseDTO;
import dev.srinivas.ProductService.dto.ProductRequestDTO;
import dev.srinivas.ProductService.dto.ProductResponseDTO;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import dev.srinivas.ProductService.model.Product;

import java.util.UUID;

public interface ProductService {

    ProductListResponseDTO getAllProducts();

    ProductResponseDTO getProductById(String authToken, UUID id) throws ProductNotFoundException;

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);


    boolean deleteProduct(int id);

    Product updateProduct(int id, Product updatedProduct);

    ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException;
}
