package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.repositories.CategoryRepository;
import com.acs560.rest_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link CategoryService} interface.
 * This service handles operations related to categories, such as 
 * retrieving, adding, updating, and deleting categories.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category to retrieve.
     * @return an {@link Optional} containing the {@link CategoryEntity} if found, otherwise empty.
     */
    @Override
    public Optional<CategoryEntity> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of {@link CategoryEntity} objects.
     */
    @Override
    public List<CategoryEntity> getCategories() {
        return (List<CategoryEntity>) categoryRepository.findAll();
    }

    /**
     * Adds a new category.
     *
     * @param category the {@link CategoryEntity} to add.
     * @return true if the category was successfully added; false if a duplicate category exists.
     * @throws Exception if there is a validation or persistence error.
     */
    @Override
    public boolean addCategory(CategoryEntity category) throws Exception {
        if (categoryRepository.existsByCategoryNameIgnoreCase(category.getCategoryName())) {
            throw new Exception("Duplicate category name: " + category.getCategoryName());
        }
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            throw new Exception("Error adding category", e);
        }
    }

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to update.
     * @param updatedCategory the {@link CategoryEntity} with updated data.
     * @return true if the category was successfully updated; false if the category does not exist.
     * @throws Exception if there is a validation or persistence error.
     */
    @Override
    public boolean updateCategory(int id, CategoryEntity updatedCategory) throws Exception {
        if (!categoryRepository.existsById(id)) {
            return false;
        }
        updatedCategory.setId(id); // Ensure the ID is set for the update
        categoryRepository.save(updatedCategory);
        return true;
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete.
     * @return true if the category was successfully deleted; false if the category does not exist.
     * @throws Exception if there is a persistence error.
     */
    @Override
    public boolean deleteCategory(int id) throws Exception {
        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
