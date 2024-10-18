package com.acs560.rest_api.services;

import com.acs560.rest_api.entities.CategoryEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing {@link CategoryEntity} entities.
 * This interface defines the contract for operations related to
 * categories, such as retrieving, adding, updating, and deleting categories.
 */
public interface CategoryService {

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category to retrieve.
     * @return an Optional containing the {@link CategoryEntity} if found, otherwise empty.
     */
    Optional<CategoryEntity> getCategory(int id);

    /**
     * Retrieves all categories.
     *
     * @return a list of {@link CategoryEntity} objects.
     */
    List<CategoryEntity> getCategories();

    /**
     * Adds a new category.
     *
     * @param category the {@link CategoryEntity} to add.
     * @return true if the category was successfully added, false if a duplicate category exists.
     * @throws Exception if there is a validation or persistence error.
     */
    boolean addCategory(CategoryEntity category) throws Exception;

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to update.
     * @param category the {@link CategoryEntity} with updated data.
     * @return true if the category was successfully updated, false if the category does not exist.
     * @throws Exception if there is a validation or persistence error.
     */
    boolean updateCategory(int id, CategoryEntity category) throws Exception;

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete.
     * @return true if the category was successfully deleted, false if the category does not exist.
     * @throws Exception if there is a persistence error.
     */
    boolean deleteCategory(int id) throws Exception;
}
