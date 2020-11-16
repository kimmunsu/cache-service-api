package com.cache.api.service;

import com.cache.api.dto.CategoryDto;
import com.cache.api.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManageService {

    private CacheService cacheService;

    public ProductManageService(@Autowired CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public List<CategoryDto> findCategoryDtoList() {
        return cacheService.getCategoryDtoList();
    }

    public ProductDto getProductDto(long productNo) {
        return cacheService.getProductDto(productNo);
    }

    public List<ProductDto> getProductDtoListByCategoryNo(int categoryNo) {
        return cacheService.getProductDtoListByCategoryNo(categoryNo);
    }
}
