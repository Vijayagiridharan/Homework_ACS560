package com.acs560.rest_api.controllers;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing categories in the REST API.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Retrieves a specific category by its ID.
     *
     * @param id the ID of the category to retrieve
     * @return ResponseEntity containing the category if found, or a 404 Not Found status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategory(@PathVariable int id) {
        Optional<CategoryEntity> categoryOpt = categoryService.getCategory(id);
        return categoryOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Retrieves all categories.
     *
     * @return ResponseEntity containing a list of all categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    /**
     * Creates a new category.
     *
     * @param category the category to create
     * @return ResponseEntity indicating the result of the creation operation
     */
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryEntity category) {
        try {
            categoryService.addCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for more insight
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Category: " + e.getMessage());
        }
    }

    /**
     * Updates an existing category.
     *
     * @param id        the ID of the category to update
     * @param category  the new data for the category
     * @return ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") int id, @RequestBody CategoryEntity category) {
        try {
            boolean updated = categoryService.updateCategory(id, category);
            if (updated) {
                return ResponseEntity.ok("Category updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Category");
        }
    }

    /**
     * Deletes a specific category by its ID.
     *
     * @param id the ID of the category to delete
     * @return ResponseEntity indicating the result of the deletion operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        try {
            boolean deleted = categoryService.deleteCategory(id);
            if (deleted) {
                return ResponseEntity.ok("Category deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Category");
        }
    }
}
