package dev.srinivas.ProductService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for JWT payload.
 */
@Getter
@Setter
public class JwtPayloadDTO {

    /**
     * The timestamp of when the token was created.
     */
    @JsonProperty("createdAt")
    private long createdAt;

    /**
     * The roles assigned to the user.
     */
    @JsonProperty("roles")
    private String[] roles;

    /**
     * The timestamp of when the token will expire.
     */
    @JsonProperty("expiryAt")
    private long expiryAt;

    /**
     * The unique identifier of the user.
     */
    @JsonProperty("userId")
    private int userId;
}