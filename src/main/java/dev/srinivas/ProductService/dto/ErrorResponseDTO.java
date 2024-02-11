package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponseDTO {
    private String message;
    private int messageCode;

}
