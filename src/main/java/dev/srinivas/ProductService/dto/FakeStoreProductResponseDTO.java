package dev.srinivas.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class FakeStoreProductResponseDTO implements Serializable {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
