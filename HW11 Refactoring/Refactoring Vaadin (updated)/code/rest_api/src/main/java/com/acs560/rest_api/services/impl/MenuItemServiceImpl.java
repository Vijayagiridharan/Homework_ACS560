package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.repositories.CategoryRepository;
import com.acs560.rest_api.services.MenuItemService;
import com.acs560.rest_api.exception.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, CategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<MenuItemEntity> getMenuItems() {
    	return (List<MenuItemEntity>) menuItemRepository.findAll();
    }

    @Override
    public MenuItemEntity getMenuItem(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("MenuItem with ID " + id + " not found"));
    }

    @Override
    public MenuItemEntity addMenuItem(MenuItemEntity menuItem) {
        validateAndSetCategory(menuItem);
        return menuItemRepository.save(menuItem);
    }

    @Override
    public void updateMenuItem(Long id, MenuItemEntity menuItem) {
        if (!menuItemRepository.existsById(id)) {
            throw new CustomNotFoundException("MenuItem with ID " + id + " not found");
        }
        validateAndSetCategory(menuItem);
        menuItem.setItemId(id);
        menuItemRepository.save(menuItem);
    }

    @Override
    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new CustomNotFoundException("MenuItem with ID " + id + " not found");
        }
        menuItemRepository.deleteById(id);
    }

    @Override
    public List<MenuItemEntity> getMenuItemsByCategory(CategoryEntity category) {
        if (category == null || category.getCategoryId() == null) {
            throw new IllegalArgumentException("Category cannot be null or invalid");
        }
        return menuItemRepository.findByCategory(category);
    }

    // Consolidated method for category validation and assignment
    private void validateAndSetCategory(MenuItemEntity menuItem) {
        if (menuItem.getCategory() == null || menuItem.getCategory().getCategoryId() == null) {
            throw new IllegalArgumentException("Category cannot be null or invalid");
        }
        CategoryEntity category = categoryRepository.findById(menuItem.getCategory().getCategoryId())
                .orElseThrow(() -> new CustomNotFoundException("Category with ID " + menuItem.getCategory().getCategoryId() + " not found"));
        menuItem.setCategory(category); // Ensure menu item is associated with a valid category
    }
}
