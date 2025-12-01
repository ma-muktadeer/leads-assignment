package com.leads.server.projection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leads.server.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);

}
