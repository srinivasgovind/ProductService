package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductListResponseDTO {
    private List<ProductResponseDTO> products;

    public ProductListResponseDTO(){
        this.products = new ArrayList<>();
    }

}
/*
    {
        "products":[]
    }
 */