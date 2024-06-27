package dev.srinivas.ProductService.repository;

import dev.srinivas.ProductService.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Product entities in Elasticsearch.
 */
@Repository
public interface OpenSearchProductElasticsearchRepository extends ElasticsearchRepository<Product, UUID> {

    /**
     * Finds all products with the exact title.
     * @param title the title of the product
     * @return a list of products with the given title
     */
    List<Product> findAllByTitle(String title);

    /**
     * Finds a product by its ID.
     * @param productId the ID of the product
     * @return an Optional containing the product if found, or empty if not found
     */
    Optional<Product> findById(Long productId);

    /**
     * Saves a product entity.
     * @param product the product entity to save
     * @return the saved product entity
     */
    Product save(Product product);

    /**
     * Finds all products containing the specified title (case insensitive), with pagination support.
     * @param title the title to search for
     * @param pageable the pagination information
     * @return a list of products containing the given title
     */
    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}