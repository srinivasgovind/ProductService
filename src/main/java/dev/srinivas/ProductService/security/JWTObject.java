package dev.srinivas.ProductService.security;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * This is a Data Transfer Object (DTO) representing a JWT payload.
 */
@Getter
@Setter
public class JWTObject {
    //This is kind of DTO Object

    private String email;

    private Long userId;

    private Date expiryAt;

    private Date createdAt;

    private List<Role> roles;
}
