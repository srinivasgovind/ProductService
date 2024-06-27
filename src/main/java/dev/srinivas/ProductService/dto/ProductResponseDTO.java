package dev.srinivas.ProductService.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Data Transfer Object for product responses.
 */
@Getter
@Setter
public class ProductResponseDTO {

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
