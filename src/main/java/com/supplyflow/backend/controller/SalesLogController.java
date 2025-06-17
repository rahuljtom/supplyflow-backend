package com.supplyflow.backend.controller;

import com.supplyflow.backend.model.Product;
import com.supplyflow.backend.model.SalesLog;
import com.supplyflow.backend.model.SalesLogRequest;
import com.supplyflow.backend.repository.ProductRepo;
import com.supplyflow.backend.repository.SalesLogRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sales")
public class SalesLogController{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SalesLogRepo salesLogRepo;

    @PostMapping
    public String LogSale(@Valid @RequestBody SalesLogRequest request){

        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        product.setQuantity(product.getQuantity() - request.getQuantity());
        productRepo.save(product);

        SalesLog sale = SalesLog.builder()
                .product(product)
                .quantity(request.getQuantity())
                .saleTime(LocalDateTime.now())
                .build();

        salesLogRepo.save(sale);

        return "Sale recorded successfully.";
    }
}

