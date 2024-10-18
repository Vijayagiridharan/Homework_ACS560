package com.acs560.rest_api.services;

import com.acs560.rest_api.models.MenuItem;

import java.util.List;

/**
 * Service interface for managing {@link MenuItem} entities.
 * <p>
 * This interface provides methods for retrieving, adding, updating, and deleting menu items,
 * as well as calculating the median price of all menu items.
 * </p>
 */
public interface MenuItemService {

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the ID of the menu item to retrieve
     * @return the {@link MenuItem} object with the specified ID
     */
    MenuItem getMenuItem(int id);

    /**
     * Retrieves a list of all menu items.
     *
     * @return a {@link List} of {@link MenuItem} objects
     */
    List<MenuItem> getMenuItems();

    /**
     * Adds a new {@link MenuItem} to the system.
     *
     * @param menuItem the {@link MenuItem} to be added
     * @return a message indicating the result of the addition operation.
     */
    String addMenuItem(MenuItem menuItem);

    /**
     * Updates an existing {@link MenuItem} with the given ID.
     *
     * @param id the unique identifier of the menu item to be updated
     * @param menuItem the updated {@link MenuItem} information
     * @return a message indicating the result of the update operation.
     */
    String updateMenuItem(int id, MenuItem menuItem);

    /**
     * Deletes a {@link MenuItem} by its unique identifier.
     *
     * @param id the unique identifier of the menu item to be deleted
     * @return a message indicating the result of the deletion operation.
     */
    String deleteMenuItem(int id);

    /**
     * Calculates the median price of all menu items.
     *
     * @return the median price of menu items. Returns 0.0 if there are no menu items.
     */
    double calculateMedianPrice();
}
