package dev.srinivas.ProductService.repository;


import dev.srinivas.ProductService.model.Product;
import dev.srinivas.ProductService.repository.CustomQueries;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
/*
CrudRepository provides only for Crud operations, but JpaRepository provides for Crud+Pagination+Sorting APIs
POJO = Plain Old Java Object(Basic Java class)
Bean = Object created in Spring Container
 */

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, UUID> {

 Product findByTitle(String title);

 Product findByTitleAndDescription(String title,String description);
 // select * from Product where title = ? and description = ?

 Product findByTitleOrDescription(String title, String description);
 //select * from Product where title = ? or description = ?

 @Query(value = CustomQueries.FIND_PRODUCT_BY_TITLE, nativeQuery = true)
 Product findProductByTitleLike(String title);

 @Query(value = "select * from products", nativeQuery=true)
 Product findAllProducts(String title, UUID id);


  List<Product> findAllByTitle(String title);

  List<Product> findAllByTitleContaining(String title, PageRequest pageable);

}
