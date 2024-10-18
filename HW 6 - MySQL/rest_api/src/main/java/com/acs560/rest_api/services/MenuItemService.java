package com.acs560.rest_api.services;

import com.acs560.rest_api.entities.MenuItemEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing {@link MenuItemEntity} entities.
 * This interface defines the contract for operations related to
 * menu items, such as retrieving, adding, updating, and deleting menu items.
 */
public interface MenuItemService {

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the ID of the menu item to retrieve.
     * @return an Optional containing the {@link MenuItemEntity} if found, otherwise empty.
     */
    Optional<MenuItemEntity> getMenuItem(int id);

    /**
     * Retrieves all menu items.
     *
     * @return a list of {@link MenuItemEntity} objects.
     */
    List<MenuItemEntity> getMenuItems();

    /**
     * Adds a new menu item.
     *
     * @param menuItem the {@link MenuItemEntity} to add.
     * @return true if the menu item was successfully added, false if a duplicate item exists.
     * @throws Exception if there is a validation or persistence error.
     */
    boolean addMenuItem(MenuItemEntity menuItem) throws Exception;

    /**
     * Updates an existing menu item.
     *
     * @param id the ID of the menu item to update.
     * @param updatedMenuItem the {@link MenuItemEntity} with updated data.
     * @return true if the menu item was successfully updated, false if the item does not exist.
     * @throws Exception if there is a validation or persistence error.
     */
    boolean updateMenuItem(int id, MenuItemEntity updatedMenuItem) throws Exception;

    /**
     * Deletes a menu item by its ID.
     *
     * @param id the ID of the menu item to delete.
     * @return true if the menu item was successfully deleted, false if the item does not exist.
     * @throws Exception if there is a persistence error.
     */
    boolean deleteMenuItem(int id) throws Exception;

    /**
     * Retrieves menu items by category.
     *
     * @param categoryName the name of the category to filter menu items.
     * @return a list of {@link MenuItemEntity} objects that belong to the specified category.
     */
    List<MenuItemEntity> getMenuItemsByCategory(String categoryName);

    /**
     * Calculates the median price of menu items.
     *
     * @return the median price as a double.
     */
    double calculateMedianPrice();
}
