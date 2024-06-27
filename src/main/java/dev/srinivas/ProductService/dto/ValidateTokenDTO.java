package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for validating a token.
 */
@Getter
@Setter
public class ValidateTokenDTO {

    /**
     * The user ID associated with the token.
     */
    private int userId;

    /**
     * The token to be validated.
     */
    private String token;

    /**
     * Default constructor for ValidateTokenDTO.
     */
    public ValidateTokenDTO() {
        // Default constructor
    }

    /**
     * Constructs a new ValidateTokenDTO with the specified user ID and token.
     * @param userId the user ID
     * @param token the token
     */
    public ValidateTokenDTO(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
