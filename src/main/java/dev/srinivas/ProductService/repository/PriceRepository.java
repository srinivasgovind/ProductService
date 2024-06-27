package dev.srinivas.ProductService.repository;


import dev.srinivas.ProductService.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for managing Price entities.
 */
public interface PriceRepository extends JpaRepository<Price, UUID> {
}
