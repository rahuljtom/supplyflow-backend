package com.supplyflow.backend.service;

import com.supplyflow.backend.model.Product;
import com.supplyflow.backend.model.SalesLog;
import com.supplyflow.backend.model.SalesLogRequest;
import com.supplyflow.backend.repository.ProductRepo;
import com.supplyflow.backend.repository.SalesLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesLogService {

    private final ProductRepo productRepo;
    private final SalesLogRepo salesLogRepo;

    @Autowired
    public SalesLogService(ProductRepo productRepo, SalesLogRepo salesLogRepo) {
        this.productRepo = productRepo;
        this.salesLogRepo = salesLogRepo;
    }

    public SalesLog createSale(SalesLogRequest request) {
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

        return salesLogRepo.save(sale);
    }

    public List<SalesLog> getFilteredSales(Long productId, LocalDateTime startDate, LocalDateTime endDate) {
        if (productId != null && startDate != null && endDate != null) {
            return salesLogRepo.findByProductIdAndSaleTimeBetween(productId, startDate, endDate);
        } else if (productId != null && startDate == null && endDate == null) {
            return salesLogRepo.findByProductId(productId);
        } else if (productId == null && startDate != null && endDate != null) {
            return salesLogRepo.findBySaleTimeBetween(startDate, endDate);
        } else {
            return salesLogRepo.findAll();
        }
    }
}