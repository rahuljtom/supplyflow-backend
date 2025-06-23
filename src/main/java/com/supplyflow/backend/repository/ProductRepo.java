package com.supplyflow.backend.repository;

import com.supplyflow.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository <Product,Long>
{

    boolean existsBySku(String sku);
}
