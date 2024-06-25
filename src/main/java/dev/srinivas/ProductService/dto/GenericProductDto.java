package dev.srinivas.ProductService.dto;


import dev.srinivas.ProductService.model.Price;
import dev.srinivas.ProductService.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private int price;
    private String category;
    private String description;
    private String image;

}
