package dev.srinivas.ProductService.model;

import dev.srinivas.ProductService.dto.GenericProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

//same name as class name then, no need to give Entity name.(optional)
@Getter
@Setter
@Entity(name = "PRODUCT")
@Document(indexName = "products") //Document = row (sql)  indexName = table(sql)// Elastic Search
public class Product extends BaseModel{
    private String title;
    @OneToOne
    private Price price;
    private String description;
    private String image;
    @ManyToOne
    private Category category;

    public GenericProductDto from(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        // genericProductDto.setPrice(product.getPrice());
        genericProductDto.setImage(product.getImage());
        return genericProductDto;
    }

}
    /*
        Product - Category: (M : 1)
        1            1
        M            1
     */