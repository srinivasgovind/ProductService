package dev.srinivas.ProductService.repository;


import dev.srinivas.ProductService.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
/**
 * Repository interface for managing Category entities.
 */
@Repository
public interface CategoryRepository  extends JpaRepository<Category, UUID> {
}
