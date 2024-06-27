package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Data Transfer Object for product requests.
 */
@Getter
@Setter
public class ProductRequestDTO {

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

    /**
     * Checks if this ProductRequestDTO is equal to another object.
     * @param o the other object
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequestDTO that = (ProductRequestDTO) o;
        return Double.compare(price, that.price) == 0 &&
                Objects.equals(title, that.title) &&
                Objects.equals(category, that.category) &&
                Objects.equals(description, that.description) &&
                Objects.equals(image, that.image);
    }

    /**
     * Returns the hash code of this ProductRequestDTO.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, price, category, description, image);
    }
}