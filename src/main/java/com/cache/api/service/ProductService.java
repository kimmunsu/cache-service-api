package com.cache.api.service;

import com.cache.api.dto.ProductModifyDto;
import com.cache.api.model.Product;
import com.cache.api.repository.ProductRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ProductService {

    private ProductRepository productRepository;

    ProductService(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void modifyProduct(ProductModifyDto productModifyDto) {
        Product product = productRepository.getOne(productModifyDto.getProductNo());
        if (!Strings.isNullOrEmpty(productModifyDto.getToProductName()))
            product.setProductName(productModifyDto.getToProductName());
        if (productModifyDto.getToPrice() != null) {
            product.setProductPrice(productModifyDto.getToPrice());
        }
    }

    public List<Product> getAllProductList() {
        return this.productRepository.findAll();
    }
}
