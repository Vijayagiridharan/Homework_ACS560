package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the MenuItemService interface.
 * This service handles operations related to menu items, such as 
 * retrieving, adding, updating, and deleting menu items.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the ID of the menu item to retrieve.
     * @return an Optional containing the MenuItemEntity if found, or empty if not found.
     */
    @Override
    public Optional<MenuItemEntity> getMenuItem(int id) {
        return menuItemRepository.findById(id);
    }

    /**
     * Retrieves all menu items.
     *
     * @return a list of all MenuItemEntity objects.
     */
    @Override
    public List<MenuItemEntity> getMenuItems() {
        return (List<MenuItemEntity>) menuItemRepository.findAll();
    }

    /**
     * Adds a new menu item.
     *
     * @param menuItem the MenuItemEntity to add.
     * @return true if the item was added successfully, false otherwise.
     * @throws Exception if an error occurs while adding the menu item.
     */
    @Override
    public boolean addMenuItem(MenuItemEntity menuItem) throws Exception {
        // Check if a menu item with the same name already exists
        List<MenuItemEntity> existingItems = menuItemRepository.findByItemName(menuItem.getItemName());
        
        if (!existingItems.isEmpty()) {
            throw new Exception("Duplicate menu item name detected: " + menuItem.getItemName());
        }

        try {
            menuItemRepository.save(menuItem);
            return true;
        } catch (Exception e) {
            throw new Exception("Error adding menu item", e);
        }
    }

    /**
     * Updates an existing menu item.
     *
     * @param id the ID of the menu item to update.
     * @param updatedMenuItem the MenuItemEntity containing updated values.
     * @return true if the item was updated successfully, false if the item does not exist.
     * @throws Exception if an error occurs while updating the menu item.
     */
    @Override
    public boolean updateMenuItem(int id, MenuItemEntity updatedMenuItem) throws Exception {
        if (menuItemRepository.existsById(id)) {
            updatedMenuItem.setItemId(id); // Ensure the ID is set for the update
            menuItemRepository.save(updatedMenuItem);
            return true;
        }
        return false;
    }

    /**
     * Deletes a menu item by its ID.
     *
     * @param id the ID of the menu item to delete.
     * @return true if the item was deleted successfully, false if the item does not exist.
     * @throws Exception if an error occurs while deleting the menu item.
     */
    @Override
    public boolean deleteMenuItem(int id) throws Exception {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Retrieves menu items by their category name, ignoring case.
     *
     * @param categoryName the name of the category to filter by.
     * @return a list of MenuItemEntity objects that belong to the specified category.
     */
    @Override
    public List<MenuItemEntity> getMenuItemsByCategory(String categoryName) {
        return menuItemRepository.findByCategoryNameIgnoreCase(categoryName);
    }

    /**
     * Calculates the median price of all menu items.
     *
     * @return the median price as a double. If there are no menu items, returns 0.
     */
    @Override
    public double calculateMedianPrice() {
        List<MenuItemEntity> menuItems = getMenuItems();
        if (menuItems.isEmpty()) {
            return 0; // Or throw an exception as per your design choice
        }

        // Use List<BigDecimal> instead of List<Double>
        List<BigDecimal> prices = menuItems.stream()
                .map(MenuItemEntity::getPrice)
                .sorted()
                .toList();

        int size = prices.size();
        if (size % 2 == 0) {
            // For even count, calculate the average of the two middle elements
            BigDecimal price1 = prices.get(size / 2 - 1);
            BigDecimal price2 = prices.get(size / 2);
            return price1.add(price2).divide(BigDecimal.valueOf(2)).doubleValue(); // Convert to double
        } else {
            // For odd count, return the middle element
            return prices.get(size / 2).doubleValue();
        }
    }
}
