package dev.srinivas.ProductService.model;

import jakarta.persistence.Entity;
import lombok.Data;

/**
 * Entity representing of Price.
 */
@Data
@Entity
public class Price extends BaseModel{
    private String currency;
    private double amount;
    private double discount;
}