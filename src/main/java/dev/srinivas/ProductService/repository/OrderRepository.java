package dev.srinivas.ProductService.repository;


import dev.srinivas.ProductService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
/**
 * Repository interface for managing Order entities.
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
