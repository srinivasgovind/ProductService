package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for a list of product responses.
 */
@Getter
@Setter
public class ProductListResponseDTO {

    /**
     * The list of product responses.
     */
    private List<ProductResponseDTO> products;

    /**
     * Default constructor that initializes the products list.
     */
    public ProductListResponseDTO() {
        this.products = new ArrayList<>();
    }
}
/*
{
    "products":[]
}
*/