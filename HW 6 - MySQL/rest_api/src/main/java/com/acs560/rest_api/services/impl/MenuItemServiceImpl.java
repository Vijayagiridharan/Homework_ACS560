package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.repositories.CategoryRepository;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the MenuItemService interface.
 * This service handles operations related to menu items, such as 
 * retrieving, adding, updating, and deleting menu items.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Optional<MenuItemEntity> getMenuItem(int id) {
        return menuItemRepository.findById(id);
    }

    @Override
    public List<MenuItemEntity> getMenuItems() {
        return (List<MenuItemEntity>) menuItemRepository.findAll();
    }

    @Override
    public boolean addMenuItem(MenuItemEntity menuItem) throws Exception {
        // Ensure that the category exists in the database
        CategoryEntity category = categoryRepository.findById(menuItem.getCategory().getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        // Set the fetched category in the menu item
        menuItem.setCategory(category);

        if (menuItemRepository.existsByItemNameIgnoreCase(menuItem.getItemName())) {
            throw new Exception("Duplicate item name: " + menuItem.getItemName());
        }
        menuItemRepository.save(menuItem);
        return true;
    }

    @Override
    public boolean updateMenuItem(int id, MenuItemEntity updatedMenuItem) throws Exception {
        if (!menuItemRepository.existsById(id)) {
            return false;
        }
        updatedMenuItem.setItemId(id); // Ensure the ID is set for the update
        menuItemRepository.save(updatedMenuItem);
        return true;
    }

    @Override
    public boolean deleteMenuItem(int id) throws Exception {
        if (!menuItemRepository.existsById(id)) {
            return false;
        }
        menuItemRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MenuItemEntity> getMenuItemsByCategory(String categoryName) {
        return menuItemRepository.findByCategory_CategoryNameIgnoreCase(categoryName);
    }

    @Override
    public double calculateMedianPrice() {
        List<MenuItemEntity> menuItems = getMenuItems();
        if (menuItems.isEmpty()) {
            return 0; // Return 0 if there are no menu items
        }

        // Extract prices as BigDecimal
        List<BigDecimal> prices = menuItems.stream()
            .map(MenuItemEntity::getPrice)
            .sorted()
            .collect(Collectors.toList());

        int size = prices.size();
        if (size % 2 == 1) {
            // If the list is odd, return the middle element
            return prices.get(size / 2).doubleValue();
        } else {
            // If the list is even, return the average of the two middle elements
            BigDecimal median = prices.get(size / 2 - 1).add(prices.get(size / 2)).divide(BigDecimal.valueOf(2));
            return median.doubleValue();
        }
    }
}
