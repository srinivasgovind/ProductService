package dev.srinivas.ProductService.exception;

/**
 * ProductNotFoundException for Custom Exceptions.
 */
public class ProductNotFoundException extends  Exception{

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
