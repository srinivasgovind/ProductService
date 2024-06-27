package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for error responses.
 */
@Getter
@Setter
public class ErrorResponseDTO {

    /**
     * The error message.
     */
    private String message;

    /**
     * The error message code.
     */
    private int messageCode;
}
