package dev.srinivas.ProductService.security;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a user role.
 */
@Getter
@Setter
@Entity
public class Role extends BaseModel{

    private String role;
}
