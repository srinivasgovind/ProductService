package dev.srinivas.ProductService.repository;


import dev.srinivas.ProductService.model.Product;
import dev.srinivas.ProductService.repository.CustomQueries;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
/**
 * Repository interface for managing Product entities with additional CRUD, pagination, and sorting operations.
 * CrudRepository provides only for CRUD operations, but JpaRepository provides for CRUD+Pagination+Sorting APIs.
 * POJO = Plain Old Java Object (Basic Java class)
 * Bean = Object created in Spring Container
 */
@Repository
public interface ProductJpaRepository extends JpaRepository<Product, UUID> {

 /**
  * Finds a product by its title.
  * @param title the title of the product
  * @return the product with the given title
  */
 Product findByTitle(String title);

 /**
  * Finds a product by its title and description.
  * @param title the title of the product
  * @param description the description of the product
  * @return the product with the given title and description
  */
 Product findByTitleAndDescription(String title, String description);
 // select * from Product where title = ? and description = ?

 /**
  * Finds a product by its title or description.
  * @param title the title of the product
  * @param description the description of the product
  * @return the product with the given title or description
  */
 Product findByTitleOrDescription(String title, String description);
 // select * from Product where title = ? or description = ?

 /**
  * Finds a product by a title pattern using a custom query.
  * @param title the title pattern to search for
  * @return the product matching the title pattern
  */
 @Query(value = CustomQueries.FIND_PRODUCT_BY_TITLE, nativeQuery = true)
 Product findProductByTitleLike(String title);

 /**
  * Finds all products using a native query.
  * @param title the title of the product
  * @param id the UUID of the product
  * @return the product matching the title and UUID
  */
 @Query(value = "select * from products", nativeQuery = true)
 Product findAllProducts(String title, UUID id);

 /**
  * Finds all products with the given title.
  * @param title the title of the product
  * @return a list of products with the given title
  */
 List<Product> findAllByTitle(String title);

 /**
  * Finds all products containing the specified title (case insensitive), with pagination support.
  * @param title the title pattern to search for
  * @param pageable the pagination information
  * @return a list of products containing the given title
  */
 List<Product> findAllByTitleContaining(String title, PageRequest pageable);
}