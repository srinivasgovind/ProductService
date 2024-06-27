package dev.srinivas.ProductService.dto;


import dev.srinivas.ProductService.model.Price;
import dev.srinivas.ProductService.model.Product;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for generic product details.
 */
@Getter
@Setter
public class GenericProductDto {

    /**
     * The unique identifier of the product.
     */
    private Long id;

    /**
     * The title of the product.
     */
    private String title;

    /**
     * The price of the product.
     */
    private int price;

    /**
     * The category of the product.
     */
    private String category;

    /**
     * The description of the product.
     */
    private String description;

    /**
     * The image URL of the product.
     */
    private String image;
}