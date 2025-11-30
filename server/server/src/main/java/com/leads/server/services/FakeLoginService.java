package com.leads.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.leads.server.classes.LoginRequest;

@Service
public class FakeLoginService {
    private final static Logger logger = LoggerFactory.getLogger(FakeLoginService.class);
    private static final String API_URL = "https://fakestoreapi.com";
    private final RestClient restClient = RestClient.create();

    public String login(LoginRequest loginRequest) {
        try {
            logger.info("Logging in with username: {}", loginRequest.getUsername());
            return restClient.post()
                    .uri(API_URL + "/auth/login")
                    .header("Content-Type", "application/json")
                    .body(loginRequest)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            logger.error("Error logging in with username: {}", loginRequest.getUsername(), e.getMessage());
            throw e;
        }
    }

}
