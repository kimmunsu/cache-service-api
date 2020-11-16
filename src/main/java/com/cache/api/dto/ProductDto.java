package com.cache.api.dto;

import com.cache.api.model.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto {

    private int categoryNo;
    private long productNo;

    private String categoryName;
    private String productName;
    private BigDecimal productPrice;

    public static ProductDto create(int categoryNo, String categoryName, Product product) {
        ProductDto productDto = new ProductDto();

        productDto.categoryNo = categoryNo;
        productDto.productNo = product.getProductNo();

        productDto.categoryName = categoryName;
        productDto.productName = product.getProductName();
        productDto.productPrice = product.getProductPrice();

        return productDto;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public long getProductNo() {
        return productNo;
    }

    public void setProductNo(long productNo) {
        this.productNo = productNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto)) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(productNo, that.productNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNo);
    }
}
