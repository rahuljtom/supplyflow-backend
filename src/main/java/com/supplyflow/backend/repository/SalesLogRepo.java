package com.supplyflow.backend.repository;

import com.supplyflow.backend.model.SalesLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface SalesLogRepo extends JpaRepository<SalesLog,Long> {

}
