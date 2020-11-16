package com.cache.api.controller;

import com.cache.api.dto.ProductDto;
import com.cache.api.service.ProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/product")
public class ProductController {

    public ProductManageService productManageService;

    public ProductController(@Autowired ProductManageService productManageService) {
        this.productManageService = productManageService;
    }

    @GetMapping("/product")
    public ProductDto getProductDto(@RequestParam("productNo") long productNo) {
        return productManageService.getProductDto(productNo);
    }

    @GetMapping("/product/list")
    public List<ProductDto> getProductDtoListByCategoryNo(@RequestParam("categoryNo") int categoryNo) {
        return productManageService.getProductDtoListByCategoryNo(categoryNo);
    }

}
