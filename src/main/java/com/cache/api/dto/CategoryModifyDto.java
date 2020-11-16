package com.cache.api.dto;

public class CategoryModifyDto {

    private int categoryNo;
    private String toCategoryName;

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getToCategoryName() {
        return toCategoryName;
    }

    public void setToCategoryName(String toCategoryName) {
        this.toCategoryName = toCategoryName;
    }
}
