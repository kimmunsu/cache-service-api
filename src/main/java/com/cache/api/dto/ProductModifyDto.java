package com.cache.api.dto;

import java.math.BigDecimal;

public class ProductModifyDto {

    private long productNo;
    private String toProductName;
    private BigDecimal toPrice;

    public long getProductNo() {
        return productNo;
    }

    public void setProductNo(long productNo) {
        this.productNo = productNo;
    }

    public String getToProductName() {
        return toProductName;
    }

    public void setToProductName(String toProductName) {
        this.toProductName = toProductName;
    }

    public BigDecimal getToPrice() {
        return toPrice;
    }

    public void setToPrice(BigDecimal toPrice) {
        this.toPrice = toPrice;
    }
}
