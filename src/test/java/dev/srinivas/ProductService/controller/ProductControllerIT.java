package dev.srinivas.ProductService.controller;

import dev.srinivas.ProductService.dto.ProductListResponseDTO;
import dev.srinivas.ProductService.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ProductController.class)
public class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "productService")
    private ProductService productService;

    //UT for controller method invoked via APIs
    @Test
    void getAllProductReturnEmptyListWhenNoProductAvailable() throws Exception {
        //arrange
        ProductListResponseDTO emptyProductListResponse = new ProductListResponseDTO();
        when(productService.getAllProducts()).thenReturn(emptyProductListResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().string("{\"products\":[]}"));
    }


}
