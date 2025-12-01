package com.leads.server.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.leads.server.entities.Product;
import com.leads.server.projection.ProductRepo;

@Service
public class ProductService {
    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getProducts() {
        logger.info("Fetching all products");
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        logger.info("Fetching product by id: " + id);
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        logger.info("Fetching products by category: " + category);
        return productRepo.findByCategory(category);
    }

    public Product createProduct(Product product) {
        logger.info("Creating product: " + product);
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        logger.info("Updating product by id: " + id);
        Product existingProduct = productRepo.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setTitle(product.getTitle());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            return productRepo.save(existingProduct);
        }
        return null;
    }

    public void deleteProductById(Long id) {
        logger.info("Deleting product by id: " + id);
        productRepo.deleteById(id);
    }

}
