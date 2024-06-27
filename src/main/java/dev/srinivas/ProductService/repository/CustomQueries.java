package dev.srinivas.ProductService.repository;

/**
 * Interface containing custom SQL queries used in the application.
 */
public interface CustomQueries {

    /**
     * SQL query to find a product by its title and ID.
     * The query uses named parameters :title and :id.
     */
    String FIND_PRODUCT_BY_TITLE = "select * from product where title like :title and id = :id";
}
