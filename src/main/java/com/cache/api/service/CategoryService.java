package com.cache.api.service;

import com.cache.api.dto.CategoryModifyDto;
import com.cache.api.model.Category;
import com.cache.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    CategoryService(@Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void modifyCategoryName(CategoryModifyDto categoryModifyDto) {
        Category category = categoryRepository.getOne(categoryModifyDto.getCategoryNo());
        category.setCategoryName(categoryModifyDto.getToCategoryName());
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

}
