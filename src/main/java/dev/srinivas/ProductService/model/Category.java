package dev.srinivas.ProductService.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Category Entity
 */
@Setter
@Getter
@Entity
public class Category extends BaseModel {

    private String categoryName;

}
