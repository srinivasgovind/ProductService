package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for creating a product in the Fake Store.
 */
@Getter
@Setter
public class FakeStoreProductRequestDTO {

    /**
     * The title of the product.
     */
    private String title;

    /**
     * The price of the product.
     */
    private double price;

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
