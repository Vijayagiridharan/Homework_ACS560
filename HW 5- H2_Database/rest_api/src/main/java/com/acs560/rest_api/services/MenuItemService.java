package com.acs560.rest_api.services;

import com.acs560.rest_api.entities.MenuItemEntity;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing {@link MenuItemEntity} entities.
 * <p>
 * This interface provides methods for retrieving, adding, updating, and deleting menu items,
 * as well as calculating the median price of all menu items.
 * </p>
 */
public interface MenuItemService {

    /**
     * Retrieves a menu item by its unique identifier.
     *
     * @param id the unique identifier of the menu item
     * @return an {@link Optional} containing the menu item if found, otherwise empty
     */
    Optional<MenuItemEntity> getMenuItem(int id);

    /**
     * Retrieves all menu items.
     *
     * @return a list of all menu items
     */
    List<MenuItemEntity> getMenuItems();

    /**
     * Adds a new menu item to the system.
     *
     * @param menuItem the menu item to be added
     * @return true if the menu item was added successfully, false otherwise
     * @throws Exception if there is an error during the addition of the menu item
     */
    boolean addMenuItem(MenuItemEntity menuItem) throws Exception;

    /**
     * Updates an existing menu item.
     *
     * @param id the unique identifier of the menu item to update
     * @param menuItem the updated menu item data
     * @return true if the menu item was updated successfully, false otherwise
     * @throws Exception if there is an error during the update
     */
    boolean updateMenuItem(int id, MenuItemEntity menuItem) throws Exception;

    /**
     * Deletes a menu item by its unique identifier.
     *
     * @param id the unique identifier of the menu item to delete
     * @return true if the menu item was deleted successfully, false otherwise
     * @throws Exception if there is an error during the deletion
     */
    boolean deleteMenuItem(int id) throws Exception;

    /**
     * Calculates the median price of all menu items.
     *
     * @return the median price of all menu items
     */
    double calculateMedianPrice();

    /**
     * Retrieves all menu items belonging to a specific category.
     *
     * @param categoryName the name of the category
     * @return a list of menu items in the specified category
     */
    List<MenuItemEntity> getMenuItemsByCategory(String categoryName);
}
