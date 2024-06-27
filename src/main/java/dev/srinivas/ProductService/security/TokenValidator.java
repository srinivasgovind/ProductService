package dev.srinivas.ProductService.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Component responsible for validating JWT tokens by making an HTTP call to the UserService.
 */
@Component
public class TokenValidator {

    private RestTemplateBuilder restTemplateBuilder;

    TokenValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    // TODO this method should call the Userservice to validate the token.
    public Optional<JWTObject> validateToken(String token) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        //Make an HTTP call to UserService to call the validateToken method.
        return Optional.empty();
    }
}
