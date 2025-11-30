package com.leads.server.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.leads.server.entities.Product;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;

@Service
public class FakeStoreService {
    private static final String API_URL = "https://fakestoreapi.com";
    private final static Logger logger = LoggerFactory.getLogger(FakeStoreService.class);
    private final RestClient restClient = RestClient.create();
    private final static ObjectMapper objectMapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    public List<Product> getProducts() throws Exception {
        try {
            String url = API_URL + "/products";
            String response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);
            // logger.info("raw response: {}", response);
            List<Product> products = objectMapper.readValue(response, new TypeReference<List<Product>>() {
            });
            return products;
        } catch (Exception e) {
            logger.error("Error fetching products from FakeStore API", e);
            throw e;
        }
    }

    public Product getProductById(Long id) {
        try {
            String url = API_URL + "/products/" + id;
            logger.info("Fetching product from FakeStore API. api:productsId=[{}:{}] ", url, id);
            String response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);
            // logger.info("raw response: {}", response);
            Product product = objectMapper.readValue(response, Product.class);
            return product;
        } catch (Exception e) {
            logger.error("Error fetching product from FakeStore API", e);
            throw e;
        }
    }

    public Product createProduct(Product product) {
        try {
            String url = API_URL + "/products";
            logger.info("Creating product from FakeStore API. api:products=[{}:{}] ", url, product.toString());
            String response = restClient.post()
                    .uri(url)
                    .header("Content-Type", "application/json")
                    .body(product)
                    .retrieve()
                    .body(String.class);
            // logger.info("raw response: {}", response);
            Product resProduct = objectMapper.readValue(response, Product.class);
            return resProduct;
        } catch (Exception e) {
            logger.error("Error creating product from FakeStore API", e);
            throw e;
        }
    }

    public Product updateProduct(Long id, Product product) {
        try {
            String url = API_URL + "/products/" + id;
            logger.info("Updating product from FakeStore API. api:productsId=[{}:{}] ", url, id);
            String response = restClient.put()
                    .uri(url)
                    .header("Content-Type", "application/json")
                    .body(product)
                    .retrieve()
                    .body(String.class);
            // logger.info("raw response: {}", response);
            Product resProduct = objectMapper.readValue(response, Product.class);
            return resProduct;
        } catch (Exception e) {
            logger.error("Error updating product from FakeStore API", e);
            throw e;
        }
    }

    public void deleteProductById(Long id) {
        try {
            String url = API_URL + "/products/" + id;
            logger.info("Deleting product from FakeStore API. api:productsId=[{}:{}] ", url, id);
            restClient.delete()
                    .uri(url)
                    .retrieve();
        } catch (Exception e) {
            logger.error("Error deleting product from FakeStore API", e);
            throw e;
        }
    }
}
