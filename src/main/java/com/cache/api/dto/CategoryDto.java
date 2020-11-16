package com.cache.api.dto;

import com.cache.api.model.Category;

public class CategoryDto {

    private int categoryNo;
    private String categoryName;

    public static CategoryDto create(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.categoryNo = category.getCategoryNo();
        categoryDto.categoryName = category.getCategoryName();
        return categoryDto;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
