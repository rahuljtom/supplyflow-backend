package com.supplyflow.backend.controller;

import com.supplyflow.backend.model.Product;
import com.supplyflow.backend.repository.ProductRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")

public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @PostMapping
    public Product createProduct (@Valid @RequestBody Product product) {
        return productRepo.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productRepo.deleteById(id);
    }

}
