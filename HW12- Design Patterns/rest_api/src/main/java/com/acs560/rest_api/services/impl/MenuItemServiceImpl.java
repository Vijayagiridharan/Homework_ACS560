package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.repositories.CategoryRepository;  // Add the CategoryRepository to check if category exists
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acs560.rest_api.exception.CustomNotFoundException;


import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;  // Inject CategoryRepository to validate category existence

    @Autowired
    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, CategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;  // Initialize category repository
    }

    @Override
    public List<MenuItemEntity> getMenuItems() {
        return (List<MenuItemEntity>) menuItemRepository.findAll();
    }

    @Override
    public MenuItemEntity getMenuItem(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItemEntity addMenuItem(MenuItemEntity menuItem) {
        // Validate category before saving the menu item
        validateCategory(menuItem.getCategory());

        // Save the menu item if category is valid
        return menuItemRepository.save(menuItem);
    }

    @Override
    public void updateMenuItem(Long id, MenuItemEntity menuItem) {
        // Validate category before updating the menu item
        validateCategory(menuItem.getCategory());

        if (menuItemRepository.existsById(id)) {
            menuItem.setItemId(id);  // Set the ID for update
            menuItemRepository.save(menuItem);
        }
    }

    @Override
    public void deleteMenuItem(Long id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
        } else {
            throw new CustomNotFoundException("MenuItem with ID " + id + " not found");
        }
    }

    @Override
    public List<MenuItemEntity> getMenuItemsByCategory(CategoryEntity category) {
        return menuItemRepository.findByCategory(category);
    }

    // Method to validate the category before adding or updating MenuItemEntity
    private void validateCategory(CategoryEntity category) {
        if (category == null || category.getCategoryId() == null) {
            throw new IllegalArgumentException("Category cannot be null or invalid");
        }

        CategoryEntity existingCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);
        if (existingCategory == null) {
            throw new CustomNotFoundException("Category with ID " + category.getCategoryId() + " not found");
        }
    }
}
