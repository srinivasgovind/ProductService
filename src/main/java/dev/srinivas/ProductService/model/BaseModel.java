package dev.srinivas.ProductService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

//This Class made as abstract, so we shouldn't able to create objects out of it
/*
1. MappedSuperclass = No table created for parent class
                  = One table for each child, includes attributes in the child class from parent class
 Typically used when we want to keep parent class as abstract

2. Table per class = very similar to MappedSuperclas, but there will be table for parent class as well
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
}
