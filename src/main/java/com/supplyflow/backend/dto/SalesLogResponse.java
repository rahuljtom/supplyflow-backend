package com.supplyflow.backend.dto;
import com.supplyflow.backend.model.SalesLog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SalesLogResponse {

    // only getters and no setters as it's read-only
    private Long id;
    private Long productId;
    private String productName;
    private int quantitySold;
    private LocalDateTime timestamp;

    public SalesLogResponse(SalesLog sale) {
        this.id = sale.getId();
        this.productId = sale.getProduct().getId();
        this.productName = sale.getProduct().getName();
        this.quantitySold = sale.getQuantity();
        this.timestamp = sale.getSaleTime(); // or sale.getTimestamp() if that's your field name
    }

}