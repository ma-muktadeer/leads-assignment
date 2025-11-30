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
import com.leads.server.services.FakeStoreService;

@RestController
@RequestMapping("/api/fake-store")
public class FakeStoreController {
    private final FakeStoreService fakeStoreService;

    public FakeStoreController(FakeStoreService fakeStoreService) {
        this.fakeStoreService = fakeStoreService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() throws Exception {
        return fakeStoreService.getProducts();
    }

    @GetMapping("/products/get/{id}")
    public Product getProductById(@PathVariable Long id) throws Exception {
        return fakeStoreService.getProductById(id);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) throws Exception {
        return fakeStoreService.getProductsByCategory(category);
    }

    @PostMapping("/products/add")
    public Product createProduct(@RequestBody Product product) throws Exception {
        return fakeStoreService.createProduct(product);
    }

    @PutMapping("/products/edit/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) throws Exception {
        return fakeStoreService.updateProduct(id, product);
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) throws Exception {
        fakeStoreService.deleteProductById(id);
    }

    @GetMapping("/ping")
    public String ping() {
        return "FakeStore API is working";
    }
}
