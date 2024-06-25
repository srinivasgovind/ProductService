package dev.srinivas.ProductService.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srinivas.ProductService.client.UserServiceClient;
import dev.srinivas.ProductService.dto.*;
import dev.srinivas.ProductService.exception.InvalidTokenException;
import dev.srinivas.ProductService.exception.ProductNotFoundException;
import dev.srinivas.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

@RestController
public class ProductController {

 @Autowired
 @Qualifier("fakeStoreProductService")
 private ProductService productService;

 private UserServiceClient userServiceClient;

private RedisTemplate<String, Object> redisTemplate;

 @Autowired // Autowired is optional here for constructor Injection
 public ProductController(@Qualifier("productService") ProductService productService, UserServiceClient userServiceClient, RedisTemplate redisTemplate){
  this.productService = productService;
  this.userServiceClient = userServiceClient;
  this.redisTemplate = redisTemplate;
 }

 private RestTemplateBuilder restTemplateBuilder;

 @GetMapping("/products/{id}")
 public ResponseEntity getProductFromId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
                                        @PathVariable("id") UUID id) throws ProductNotFoundException {

 ProductResponseDTO response = productService.getProductById(authToken, id);
 return  ResponseEntity.ok(response);
 }

 @GetMapping("/products")
 public ResponseEntity getAllProducts(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken){

  ProductListResponseDTO response = productService.getAllProducts();

  return  ResponseEntity.ok(response);
 }
 @PostMapping("/products")
 public ResponseEntity createProduct(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken, @RequestBody ProductRequestDTO productRequestDTO) throws Exception {
    validateUser(authToken);
   ProductResponseDTO requestDTO = productService.createProduct(productRequestDTO);

   return ResponseEntity.ok(requestDTO);
 }

 @DeleteMapping("/products/{id}")
 public ResponseEntity deleteProductById(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken, @PathVariable("id") int id){
    boolean response = productService.deleteProduct(id);

    return ResponseEntity.ok(response);
 }

 @GetMapping("/products/title/{title}")
 public ResponseEntity getProductByTitle(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken, @PathVariable("title") String title) throws ProductNotFoundException {

  ProductResponseDTO response = productService.findProductByTitle(title);
  return  ResponseEntity.ok(response);
 }
 private void validateUser(String token) throws Exception {
  String[] chunks = token.split("\\.");
  Base64.Decoder decoder = Base64.getUrlDecoder();
  String payload = new String(decoder.decode(chunks[1]));
  ObjectMapper mapper = new ObjectMapper();
  JwtPayloadDTO jwtPayload = mapper.readValue(payload, JwtPayloadDTO.class);
  int userId = jwtPayload.getUserId();
  ValidateTokenDTO validateTokenDTO = new ValidateTokenDTO(userId, token);
  String result = userServiceClient.validateToken(validateTokenDTO);
  if (!result.contains(SessionStatus.ACTIVE.name())) {
   throw new InvalidTokenException("Token is not valid");
  }
 }
}
