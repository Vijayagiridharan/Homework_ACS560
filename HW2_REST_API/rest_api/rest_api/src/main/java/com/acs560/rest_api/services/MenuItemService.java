package com.acs560.rest_api.services;

import com.acs560.rest_api.models.MenuItem;
import java.util.List;

/**
 * Service interface for managing {@link MenuItem} objects.
 * Provides methods for performing CRUD operations and analysis on menu items.
 */
public interface MenuItemService {

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the ID of the menu item to retrieve
     * @return the {@link MenuItem} with the specified ID
     */
    MenuItem getMenuItem(int id);

    /**
     * Retrieves all menu items.
     *
     * @return a {@link List} of all {@link MenuItem} objects
     */
    List<MenuItem> getMenuItems();

    /**
     * Retrieves menu items filtered by a specific category.
     *
     * @param categoryName the name of the category to filter by
     * @return a {@link List} of {@link MenuItem} objects in the specified category
     */
    List<MenuItem> getMenuItemsByCategory(String categoryName);

    /**
     * Creates a new menu item.
     *
     * @param menuItem the {@link MenuItem} object to create
     * @return the created {@link MenuItem} object
     */
    MenuItem createMenuItem(MenuItem menuItem);

    /**
     * Updates an existing menu item.
     *
     * @param id the ID of the menu item to update
     * @param menuItem the {@link MenuItem} object with updated details
     * @return the updated {@link MenuItem} object
     */
    MenuItem updateMenuItem(int id, MenuItem menuItem);

    /**
     * Deletes a menu item by its ID.
     *
     * @param id the ID of the menu item to delete
     */
    void deleteMenuItem(int id);

    /**
     * Calculates the average price of menu items in a specific category.
     *
     * @param categoryName the name of the category to calculate the average price for
     * @return the average price of menu items in the specified category
     */
    double calculateAveragePrice(String categoryName);

    /**
     * Calculates the average price of all menu items.
     *
     * @return the average price of all menu items
     */
    double calculateAveragePrice();

    /**
     * Calculates the median price of all menu items.
     *
     * @return the median price of all menu items
     */
    double calculateMedianPrice();
}
