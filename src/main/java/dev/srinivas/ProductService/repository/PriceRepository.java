package dev.srinivas.ProductService.repository;


import dev.srinivas.ProductService.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
}
