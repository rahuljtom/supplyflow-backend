package com.supplyflow.backend.service;

import com.supplyflow.backend.model.Product;
import com.supplyflow.backend.repository.ProductRepo;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void uploadProductsFromCSV(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());

            int rowNumber = 1;
            for (CSVRecord record : parser) {
                rowNumber++;
                try {
                    String name = record.get("name");
                    String sku = record.get("sku");
                    String category = record.get("category");
                    String supplier = record.get("supplier");
                    int quantity = Integer.parseInt(record.get("quantity"));

                    // Basic validation
                    if (name.isBlank() || sku.isBlank() || category.isBlank() || supplier.isBlank()) {
                        throw new IllegalArgumentException("Fields cannot be blank");
                    }

                    if (quantity < 0) {
                        throw new IllegalArgumentException("Quantity must be >= 0");
                    }

                    if (productRepo.existsBySku(sku)) {
                        continue; // Skip duplicate SKU
                    }

                    Product product = Product.builder()
                            .name(name)
                            .sku(sku)
                            .quantity(quantity)
                            .category(category)
                            .supplier(supplier)
                            .build();

                    productRepo.save(product);
                } catch (Exception e) {
                    System.err.println("Row " + rowNumber + " skipped: " + e.getMessage());
                }
            }
        }
    }
}