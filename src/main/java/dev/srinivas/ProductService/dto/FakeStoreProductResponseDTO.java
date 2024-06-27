package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * Data Transfer Object for the response of a product in the Fake Store.
 */
@Getter
@Setter
public class FakeStoreProductResponseDTO implements Serializable {

    /**
     * The unique identifier of the product.
     */
    private UUID id;

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