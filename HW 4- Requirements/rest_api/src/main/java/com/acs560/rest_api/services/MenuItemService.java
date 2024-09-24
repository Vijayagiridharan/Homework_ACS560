package com.acs560.rest_api.services;

import com.acs560.rest_api.models.MenuItem;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Service interface for managing {@link MenuItem} entities.
 * <p>
 * This interface provides methods for retrieving, adding, updating, and deleting menu items,
 * as well as calculating the median price of all menu items.
 * </p>
 */
@Service
public interface MenuItemService {

    /**
     * Retrieves a {@link MenuItem} by its unique identifier.
     *
     * @param id the unique identifier of the menu item
     * @return the {@link MenuItem} with the specified id, or null if not found
     */
    MenuItem getMenuItem(int id);

    /**
     * Retrieves a list of all {@link MenuItem} entities.
     *
     * @return a list of all menu items
     */
    List<MenuItem> getMenuItems();

    /**
     * Adds a new {@link MenuItem} to the system.
     *
     * @param menuItem the {@link MenuItem} to be added
     * @return true if the menu item was successfully added, false otherwise
     * @throws Exception if an error occurs during the addition of the menu item
     */
    boolean addMenuItem(MenuItem menuItem) throws Exception;

    /**
     * Updates an existing {@link MenuItem} with the given id.
     *
     * @param id the unique identifier of the menu item to be updated
     * @param menuItem the updated {@link MenuItem} information
     * @return true if the menu item was successfully updated, false otherwise
     * @throws Exception if an error occurs during the update of the menu item
     */
    boolean updateMenuItem(int id, MenuItem menuItem) throws Exception;

    /**
     * Deletes a {@link MenuItem} by its unique identifier.
     *
     * @param id the unique identifier of the menu item to be deleted
     * @return true if the menu item was successfully deleted, false otherwise
     * @throws Exception if an error occurs during the deletion of the menu item
     */
    boolean deleteMenuItem(int id) throws Exception;

    /**
     * Calculates the median price of all {@link MenuItem} entities.
     *
     * @return the median price of all menu items
     */
    double calculateMedianPrice();
    /**
     * Retrieves a list of MenuItems that belong to the specified category.
     *
     * @param categoryName the name of the category for which to retrieve the menu items
     * @return a list of MenuItem objects that belong to the specified category, 
     *         or an empty list if no items are found in that category
     * @throws Exception if there is an issue retrieving the menu items
     */
    List<MenuItem> getMenuItemsByCategory(String categoryName);
    
    
}
