package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.models.MenuItem;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link MenuItemService} interface for managing menu items.
 * Provides CRUD operations and analysis functions for menu items.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the ID of the menu item to retrieve
     * @return the {@link MenuItem} with the specified ID, or {@code null} if not found
     */
    @Override
    public MenuItem getMenuItem(int id) {
        return MenuItemRepository.getMenuItems().stream()
                .filter(item -> item.getItemId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves all menu items.
     *
     * @return a list of all {@link MenuItem}s
     */
    @Override
    public List<MenuItem> getMenuItems() {
        return MenuItemRepository.getMenuItems();
    }

    /**
     * Retrieves menu items that belong to a specific category.
     *
     * @param categoryName the category name to filter by
     * @return a list of {@link MenuItem}s that belong to the specified category
     */
    @Override
    public List<MenuItem> getMenuItemsByCategory(String categoryName) {
        return MenuItemRepository.getMenuItems().stream()
                .filter(item -> item.getCategoryName().equalsIgnoreCase(categoryName))
                .toList();
    }

    /**
     * Creates a new menu item and adds it to the repository.
     *
     * @param menuItem the {@link MenuItem} to create
     * @return the created {@link MenuItem} with an assigned ID
     */
    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        List<MenuItem> menuItems = MenuItemRepository.getMenuItems();
        menuItem.setItemId(menuItems.size() + 1); // Generate new ID (simplistic)
        menuItems.add(menuItem);
        return menuItem;
    }

    /**
     * Updates an existing menu item with new data.
     *
     * @param id the ID of the menu item to update
     * @param menuItem the {@link MenuItem} containing updated data
     * @return the updated {@link MenuItem}, or {@code null} if the item with the specified ID does not exist
     */
    @Override
    public MenuItem updateMenuItem(int id, MenuItem menuItem) {
        List<MenuItem> menuItems = MenuItemRepository.getMenuItems();
        Optional<MenuItem> existingItem = menuItems.stream()
                .filter(item -> item.getItemId() == id)
                .findFirst();

        if (existingItem.isPresent()) {
            MenuItem itemToUpdate = existingItem.get();
            itemToUpdate.setItemId(menuItem.getItemId());
            itemToUpdate.setPrice(menuItem.getPrice());
            itemToUpdate.setCalories(menuItem.getCalories());
            itemToUpdate.setCuisineType(menuItem.getCuisineType());
            itemToUpdate.setVegetarian(menuItem.isVegetarian());
            itemToUpdate.setSpicyLevel(menuItem.getSpicyLevel());
            itemToUpdate.setAvailability(menuItem.isAvailability());
            itemToUpdate.setCategoryName(menuItem.getCategoryName());
            itemToUpdate.setDescription(menuItem.getDescription());
            return itemToUpdate;
        }
        return null;
    }

    /**
     * Deletes a menu item by its ID.
     *
     * @param id the ID of the menu item to delete
     */
    @Override
    public void deleteMenuItem(int id) {
        List<MenuItem> menuItems = MenuItemRepository.getMenuItems();
        menuItems.removeIf(item -> item.getItemId() == id);
    }

    /**
     * Calculates the average price of menu items in a specific category.
     *
     * @param categoryName the category name to filter by
     * @return the average price of menu items in the specified category, or {@code 0.0} if no items are found
     */
    @Override
    public double calculateAveragePrice(String categoryName) {
        List<MenuItem> menuItems = MenuItemRepository.getMenuItems().stream()
                .filter(item -> item.getCategoryName().equalsIgnoreCase(categoryName))
                .collect(Collectors.toList());

        return menuItems.stream()
                .mapToDouble(MenuItem::getPrice)
                .average()
                .orElse(0.0);
    }

    /**
     * Calculates the average price of all menu items.
     *
     * @return the average price of all menu items, or {@code 0.0} if no items are found
     */
    @Override
    public double calculateAveragePrice() {
        List<MenuItem> menuItems = MenuItemRepository.getMenuItems();

        return menuItems.stream()
                .mapToDouble(MenuItem::getPrice)
                .average()
                .orElse(0.0);
    }

    /**
     * Calculates the median price of all menu items.
     *
     * @return the median price of all menu items, or {@code 0.0} if no items are found
     */
    @Override
    public double calculateMedianPrice() {
        List<MenuItem> menuItems = MenuItemRepository.getMenuItems();
        
        List<Double> sortedPrices = menuItems.stream()
                .map(MenuItem::getPrice)
                .sorted()
                .collect(Collectors.toList());

        int size = sortedPrices.size();
        if (size == 0) {
            return 0.0;  // No menu items, so return 0
        }

        if (size % 2 == 1) {
            return sortedPrices.get(size / 2);
        } else {
            return (sortedPrices.get(size / 2 - 1) + sortedPrices.get(size / 2)) / 2.0;
        }
    }
}
