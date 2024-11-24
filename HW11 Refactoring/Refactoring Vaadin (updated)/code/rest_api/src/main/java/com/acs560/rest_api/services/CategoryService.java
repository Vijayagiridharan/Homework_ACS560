package com.acs560.rest_api.services;

import com.acs560.rest_api.entities.CategoryEntity;
import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getCategories();
    CategoryEntity getCategory(Long id);  // Changed to Long
    CategoryEntity addCategory(CategoryEntity category);
    void deleteCategory(Long id);  // Changed to Long
    void updateCategory(Long categoryId, CategoryEntity category);  // Changed to Long
}
