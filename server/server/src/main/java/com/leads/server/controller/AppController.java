package com.leads.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leads.server.entities.Product;
import com.leads.server.services.ProductService;

@RestController
@RequestMapping("/api/app")
public class AppController {
    private final ProductService productService;

    public AppController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() throws Exception {
        return productService.getProducts();
    }

    @GetMapping("/products/get/{id}")
    public Product getProductById(@PathVariable Long id) throws Exception {
        return productService.getProductById(id);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) throws Exception {
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/products/add")
    public Product createProduct(@RequestBody Product product) throws Exception {
        return productService.createProduct(product);
    }

    @PutMapping("/products/edit/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) throws Exception {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
    }

    @GetMapping("/ping")
    public String appEndpoint() {
        return "App endpoint";
    }
}
