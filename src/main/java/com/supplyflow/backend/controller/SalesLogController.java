package com.supplyflow.backend.controller;

import com.supplyflow.backend.dto.SalesLogResponse;
import com.supplyflow.backend.model.SalesLog;
import com.supplyflow.backend.model.SalesLogRequest;
import com.supplyflow.backend.service.SalesLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesLogController{

    @Autowired
    private SalesLogService salesLogService;

    @PostMapping
    public ResponseEntity<SalesLogResponse> createSale(@Valid @RequestBody SalesLogRequest request) {
        var sale = salesLogService.createSale(request);
        SalesLogResponse response = new SalesLogResponse(sale);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<SalesLogResponse> getSales(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<SalesLog> logs = salesLogService.getFilteredSales(productId, startDate, endDate);
        return logs.stream()
                .map(SalesLogResponse::new)
                .toList();
    }
}
