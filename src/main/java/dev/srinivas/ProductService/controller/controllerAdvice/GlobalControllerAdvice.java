package dev.srinivas.ProductService.controller.controllerAdvice;


import dev.srinivas.ProductService.dto.ErrorResponseDTO;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for all controllers.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Handles ProductNotFoundException and returns a formatted error response.
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing the error response DTO and HTTP status
     */
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setMessageCode(404);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}