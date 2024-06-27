package dev.srinivas.ProductService.exception;

/**
 * InvalidTitleException for Custom Exceptions.
 */
public class InvalidTitleException extends RuntimeException{

    public InvalidTitleException(){

    }

    public InvalidTitleException(String message){
        super(message);
    }
}
