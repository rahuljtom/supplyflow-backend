package com.supplyflow.backend.repository;

import com.supplyflow.backend.model.SalesLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface SalesLogRepo extends JpaRepository<SalesLog,Long> {

    List<SalesLog> findByProductIdAndSaleTimeBetween(Long productId, LocalDateTime start, LocalDateTime end);
    List<SalesLog> findByProductId(Long productId);
    List<SalesLog> findBySaleTimeBetween(LocalDateTime start, LocalDateTime end);
}
