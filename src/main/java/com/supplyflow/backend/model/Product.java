package com.supplyflow.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="product name is required")
    private String name;

    @NotBlank(message="SKU is required")
    private String sku;

    @Min(value=0,message ="quantity cannot be negative")
    private int quantity;

    @NotBlank(message="category is required")
    private String category;

    @NotBlank(message="supplier is required")
    private String supplier;
}
