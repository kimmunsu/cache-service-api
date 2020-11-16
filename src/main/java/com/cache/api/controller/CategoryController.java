package com.cache.api.controller;

import com.cache.api.dto.CategoryDto;
import com.cache.api.service.ProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/category")
public class CategoryController {

    private ProductManageService productManageService;

    public CategoryController(@Autowired ProductManageService productManageService) {
        this.productManageService = productManageService;
    }

    @GetMapping("/list")
    public List<CategoryDto> list() {
        return productManageService.findCategoryDtoList();
    }

}
