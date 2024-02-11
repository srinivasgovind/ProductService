package dev.srinivas.ProductService.client;

import dev.srinivas.ProductService.dto.FakeStoreProductRequestDTO;
import dev.srinivas.ProductService.dto.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreAPIClient {

    private String fakeStoreAPIURL;

    @Value("${fakestore.api.path.product}")
    private String fakeStoreAPIPathProduct;

    private RestTemplateBuilder restTemplateBuilder;




    public FakeStoreAPIClient(RestTemplateBuilder restTemplateBuilder,
                              @Value("${fakestore.api.url}") String fakeStoreAPIURL){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIURL = fakeStoreAPIURL;
    }

    public FakeStoreProductResponseDTO createProduct(FakeStoreProductRequestDTO fakeStoreProductRequestDTO){
        String createProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.postForEntity(createProductURL,fakeStoreProductRequestDTO, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public FakeStoreProductResponseDTO getProductById(int id){

        String getProductByUrlId = fakeStoreAPIURL + fakeStoreAPIPathProduct +"/"+ id;

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.getForEntity(getProductByUrlId, FakeStoreProductResponseDTO.class);

        return productResponse.getBody();
    }

    public List<FakeStoreProductResponseDTO> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String getAllProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct;

        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseArray = restTemplate.getForEntity(getAllProductURL, FakeStoreProductResponseDTO[].class);

       return List.of(productResponseArray.getBody());
    }

    public void deleteProduct(int id){
        String productDeleteURL = fakeStoreAPIURL + fakeStoreAPIPathProduct +"/"+ id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(productDeleteURL);
    }
}
