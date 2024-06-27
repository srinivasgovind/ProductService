package dev.srinivas.ProductService.client;

import dev.srinivas.ProductService.dto.FakeStoreProductRequestDTO;
import dev.srinivas.ProductService.dto.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Client class for interacting with the Fake Store API.
 */
@Component
public class FakeStoreAPIClient {

    private String fakeStoreAPIURL;

    @Value("${fakestore.api.path.product}")
    private String fakeStoreAPIPathProduct;

    private RestTemplateBuilder restTemplateBuilder;

    /**
     * Constructor for FakeStoreAPIClient.
     * @param restTemplateBuilder the RestTemplate builder
     * @param fakeStoreAPIURL the base URL of the Fake Store API
     */
    public FakeStoreAPIClient(RestTemplateBuilder restTemplateBuilder,
                              @Value("${fakestore.api.url}") String fakeStoreAPIURL) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIURL = fakeStoreAPIURL;
    }

    /**
     * Creates a new product in the Fake Store.
     * @param fakeStoreProductRequestDTO the product details to create
     * @return the response containing the created product details
     */
    public FakeStoreProductResponseDTO createProduct(FakeStoreProductRequestDTO fakeStoreProductRequestDTO) {
        String createProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.postForEntity(createProductURL, fakeStoreProductRequestDTO, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    /**
     * Retrieves a product by its ID from the Fake Store.
     * @param id the ID of the product to retrieve
     * @return the response containing the product details
     */
    public FakeStoreProductResponseDTO getProductById(int id) {
        String getProductByUrlId = fakeStoreAPIURL + fakeStoreAPIPathProduct + "/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.getForEntity(getProductByUrlId, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    /**
     * Retrieves all products from the Fake Store.
     * @return a list of all products
     */
    public List<FakeStoreProductResponseDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String getAllProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct;
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseArray = restTemplate.getForEntity(getAllProductURL, FakeStoreProductResponseDTO[].class);
        return List.of(productResponseArray.getBody());
    }

    /**
     * Deletes a product by its ID from the Fake Store.
     * @param id the ID of the product to delete
     */
    public void deleteProduct(int id) {
        String productDeleteURL = fakeStoreAPIURL + fakeStoreAPIPathProduct + "/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(productDeleteURL);
    }
}