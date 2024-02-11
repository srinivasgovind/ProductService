package dev.srinivas.ProductService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//same name as class name then, no need to give Entity name.(optional)
@Getter
@Setter
@Entity(name = "PRODUCT")
public class Product extends BaseModel{
    private String title;
    @OneToOne
    private Price price;
    private String description;
    private String image;
    @ManyToOne
    private Category category;



}
    /*
        Product - Category: (M : 1)
        1            1
        M            1
     */