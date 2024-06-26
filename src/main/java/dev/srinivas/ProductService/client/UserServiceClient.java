package dev.srinivas.ProductService.client;


import dev.srinivas.ProductService.dto.ValidateTokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Wrapper on UserService APIs.
 */
@Component
public class UserServiceClient {

    private RestTemplateBuilder restTemplateBuilder;
    private String userServiceAPIURL;

    @Value("${userservice.api.path.validate}")
    private String userServiceValidatePath;

    /**
     * Constructor for UserServiceClient.
     * @param restTemplateBuilder the RestTemplate builder
     * @param userServiceAPIURL the base URL of the UserService API
     */
    public UserServiceClient(RestTemplateBuilder restTemplateBuilder,
                             @Value("${userservice.api.url}") String userServiceAPIURL) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.userServiceAPIURL = userServiceAPIURL;
    }

    /**
     * Validates a token using the UserService API.
     * @param validateTokenDTO the token validation request data
     * @return the response from the UserService API
     */
    public String validateToken(ValidateTokenDTO validateTokenDTO) {
        String validateTokenURL = userServiceAPIURL + userServiceValidatePath;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> validateTokenResponse =
                restTemplate.postForEntity(validateTokenURL, validateTokenDTO, String.class);
        return validateTokenResponse.getBody();
    }
}