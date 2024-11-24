package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.repositories.CategoryRepository;
import com.acs560.rest_api.services.CategoryService;
import com.acs560.rest_api.exception.CustomNotFoundException;
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
    public CategoryEntity getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Category with ID " + id + " not found"));
    }

    @Override
    public CategoryEntity addCategory(CategoryEntity category) {
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CustomNotFoundException("Category with ID " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(Long categoryId, CategoryEntity category) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CustomNotFoundException("Category with ID " + categoryId + " not found");
        }
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        category.setCategoryId(categoryId);
        categoryRepository.save(category);
    }
}
