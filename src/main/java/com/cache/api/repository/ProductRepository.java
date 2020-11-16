package com.cache.api.repository;

import com.cache.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getByCategoryNo(int categoryNo);
}
