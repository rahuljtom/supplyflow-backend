// This is only to accept the fields we need from the request not the entire SalesLog entity).
package com.supplyflow.backend.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalesLogRequest {

    @NotNull(message="Product id is required")
    private Long productId;

    @Min(value=1,message="quantity must be atleast 1")
    private int quantity;
}
