package com.supplyflow.backend.controller;

import com.supplyflow.backend.dto.SalesLogResponse;
import com.supplyflow.backend.model.SalesLogRequest;
import com.supplyflow.backend.service.SalesLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
