package com.cache.api.controller;

import com.cache.api.dto.CategoryDto;
import com.cache.api.dto.CategoryModifyDto;
import com.cache.api.dto.ProductDto;
import com.cache.api.dto.ProductModifyDto;
import com.cache.api.service.CategoryService;
import com.cache.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private CategoryService categoryService;
    private ProductService productService;

    AdminController(@Autowired CategoryService categoryService,
                    @Autowired ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostMapping("/category/name/modify")
    public void modifyCategoryName(@RequestBody CategoryModifyDto categoryModifyDto) {
        categoryService.modifyCategoryName(categoryModifyDto);
    }

    @PostMapping("/product/modify")
    public void modifyProductName(@RequestBody ProductModifyDto productModifyDto) {
        productService.modifyProduct(productModifyDto);
    }


}
