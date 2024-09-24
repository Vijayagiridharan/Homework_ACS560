package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.models.MenuItem;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link MenuItemService} interface that handles the business logic
 * for managing menu items.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the ID of the menu item to retrieve
     * @return the {@link MenuItem} object with the specified ID
     */
    @Override
    public MenuItem getMenuItem(int id) {
        return menuItemRepository.getMenuItem(id);
    }

    /**
     * Retrieves a list of all menu items.
     *
     * @return a {@link List} of {@link MenuItem} objects
     */
    @Override
    public List<MenuItem> getMenuItems() {
        return menuItemRepository.getMenuItems();
    }

    /**
     * Adds a new menu item.
     *
     * @param menuItem the {@link MenuItem} object to add
     * @return true if the menu item was added successfully, false otherwise
     * @throws Exception if an error occurs while adding the menu item
     */
    @Override
    public boolean addMenuItem(MenuItem menuItem) throws Exception {
        return menuItemRepository.addMenuItem(menuItem);
    }

    /**
     * Updates an existing menu item.
     *
     * @param id the ID of the menu item to update
     * @param menuItem the {@link MenuItem} object with updated information
     * @return true if the menu item was updated successfully, false otherwise
     * @throws Exception if an error occurs while updating the menu item
     */
    @Override
    public boolean updateMenuItem(int id, MenuItem menuItem) throws Exception {
        return menuItemRepository.updateMenuItem(id, menuItem);
    }

    /**
     * Deletes a menu item by its ID.
     *
     * @param id the ID of the menu item to delete
     * @return true if the menu item was deleted successfully, false otherwise
     * @throws Exception if an error occurs while deleting the menu item
     */
    @Override
    public boolean deleteMenuItem(int id) throws Exception {
        return menuItemRepository.deleteMenuItem(id);
    }

    /**
     * Calculates the median price of all menu items.
     *
     * @return the median price of menu items. Returns 0.0 if there are no menu items.
     */
    @Override
    public double calculateMedianPrice() {
        List<Double> prices = menuItemRepository.getMenuItems()
                .stream()
                .map(MenuItem::getPrice)
                .sorted()
                .collect(Collectors.toList());

        int size = prices.size();
        if (size == 0) {
            return 0.0; // Return 0 if there are no items
        }

        if (size % 2 == 1) {
            // If odd number of prices, return the middle one
            return prices.get(size / 2);
        } else {
            // If even number of prices, return the average of the two middle ones
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        }
    }
    
    /**
     * Retrieves a list of menu items that belong to the specified category.
     *
     * This method interacts with the repository to fetch all menu items
     * that match the provided category name. The category name is case-insensitive,
     * allowing for flexibility in user input.
     *
     * @param categoryName The name of the category for which menu items are to be retrieved.
     *                     This parameter should not be null or empty.
     * @return A list of {@link MenuItem} objects that belong to the specified category.
     *         If no items are found, an empty list is returned.
     * @throws IllegalArgumentException if the categoryName is null or empty.
     */
    @Override
    public List<MenuItem> getMenuItemsByCategory(String categoryName) {
        return menuItemRepository.getMenuItemsByCategory(categoryName);
    }
    
}
