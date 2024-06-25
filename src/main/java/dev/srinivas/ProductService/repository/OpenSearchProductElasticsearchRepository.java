package dev.srinivas.ProductService.repository;

import dev.srinivas.ProductService.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OpenSearchProductElasticsearchRepository extends ElasticsearchRepository
        <Product, UUID> {

    List<Product> findAllByTitle(String title);

    Optional<Product> findById(Long productId);

    Product save(Product product);

    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
