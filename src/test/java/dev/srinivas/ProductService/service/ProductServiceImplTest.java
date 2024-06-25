package dev.srinivas.ProductService.service;



import dev.srinivas.ProductService.dto.ProductResponseDTO;
import dev.srinivas.ProductService.exception.InvalidTitleException;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import dev.srinivas.ProductService.model.Category;
import dev.srinivas.ProductService.model.Price;
import dev.srinivas.ProductService.model.Product;
import dev.srinivas.ProductService.repository.ProductJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {


    @Mock // we need a mock object of the given attribute
    private ProductJpaRepository productJpaRepository;

    @InjectMocks // this is the class we want to test, and this is where we would inject mock objects
    private  ProductServiceImpl productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this); //creates auto closeable resources
    }

    @Test
    public void testFindProductByTitleSuccess() throws ProductNotFoundException {
        //Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);
        mockPrice.setCurrency("INR");
        mockPrice.setDiscount(0);
        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");
        String testTitle = "testProductTitle";
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle("testProductTitle");
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productJpaRepository.findByTitle(any())).thenReturn(mockProduct);
        //Act
        ProductResponseDTO actualResponse = productService.findProductByTitle(testTitle);

        //Assert
        Assertions.assertEquals(actualResponse.getTitle(),mockProduct.getTitle());

    }


    @Test
    public void testFindByProductByTitle_RepoRespondsWithNullObject() throws ProductNotFoundException{
        //Arrange
        String testTitle = "TestProductTitle";
        when(productJpaRepository.findByTitle(testTitle)).thenReturn(null);
        Assertions.assertThrows(ProductNotFoundException.class,  ()->productService.findProductByTitle(testTitle));

    }

    @Test
    public void testFindProductByTitle_NullTitle() throws ProductNotFoundException {
        //Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);
        mockPrice.setCurrency("INR");
        mockPrice.setDiscount(0);
        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");
        String testTitle = null;
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle("testProductTitle");
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productJpaRepository.findByTitle(testTitle)).thenReturn(mockProduct);
        //Assert and Act
        Assertions.assertThrows(InvalidTitleException.class, ()-> productService.findProductByTitle(testTitle));

    }

}
