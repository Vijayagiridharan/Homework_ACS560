package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.repositories.CategoryRepository;
import com.acs560.rest_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> getCategories() {
        return (List<CategoryEntity>) categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategory(Long id) {  // Changed to Long
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryEntity addCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {  // Changed to Long
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(Long categoryId, CategoryEntity category) {  // Changed to Long
        if (categoryRepository.existsById(categoryId)) {
            category.setCategoryId(categoryId);
            categoryRepository.save(category);
        }
    }
}
