package dev.srinivas.ProductService.controller;


import dev.srinivas.ProductService.dto.ProductListResponseDTO;
import dev.srinivas.ProductService.dto.ProductRequestDTO;
import dev.srinivas.ProductService.dto.ProductResponseDTO;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import dev.srinivas.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

// @Autowired
// @Qualifier("fakeStoreProductService")
 private ProductService productService;

 @Autowired // Autowired is optional here for constructor Injection
 public ProductController(@Qualifier("productService") ProductService productService){
  this.productService = productService;
 }

 private RestTemplateBuilder restTemplateBuilder;

 @GetMapping("/products/{id}")
 public ResponseEntity getProductFromId(@PathVariable("id") int id) throws ProductNotFoundException {

 ProductResponseDTO response = productService.getProductById(id);
 return  ResponseEntity.ok(response);
 }

 @GetMapping("/products")
 public ResponseEntity getAllProducts(){

  ProductListResponseDTO response = productService.getAllProducts();

  return  ResponseEntity.ok(response);
 }
 @PostMapping("/products")
 public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO){
   ProductResponseDTO requestDTO = productService.createProduct(productRequestDTO);

   return ResponseEntity.ok(requestDTO);
 }

 @DeleteMapping("/products/{id}")
 public ResponseEntity deleteProductById(@PathVariable("id") int id){
    boolean response = productService.deleteProduct(id);

    return ResponseEntity.ok(response);
 }

 @GetMapping("/products/title/{title}")
 public ResponseEntity getProductByTitle(@PathVariable("title") String title) throws ProductNotFoundException {

  ProductResponseDTO response = productService.findProductByTitle(title);
  return  ResponseEntity.ok(response);
 }

}
