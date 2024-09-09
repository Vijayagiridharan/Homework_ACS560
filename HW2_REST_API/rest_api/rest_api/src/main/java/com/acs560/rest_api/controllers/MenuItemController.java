package com.acs560.rest_api.controllers;

import com.acs560.rest_api.models.MenuItem;
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing menu items.
 * Provides endpoints for CRUD operations on menu items and some additional analysis functionalities.
 */
@RestController
@RequestMapping("/api/v1/menuItems")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    /**
     * Retrieve a menu item by its ID.
     *
     * @param id the ID of the menu item
     * @return the menu item with the specified ID
     */
    @GetMapping("/{id}")
    public MenuItem getMenuItem(@PathVariable int id) {
        return menuItemService.getMenuItem(id);
    }

    /**
     * Retrieve all menu items.
     *
     * @return a list of all menu items
     */
    @GetMapping
    public List<MenuItem> getMenuItems() {
        return menuItemService.getMenuItems();
    }

    /**
     * Retrieve menu items by category name.
     *
     * @param categoryName the category name of the menu items
     * @return a list of menu items belonging to the specified category
     */
    @GetMapping("/category/{categoryName}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable String categoryName) {
        return menuItemService.getMenuItemsByCategory(categoryName);
    }

    /**
     * Create a new menu item.
     *
     * @param menuItem the menu item to be created
     * @return the newly created menu item
     */
    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.createMenuItem(menuItem);
    }

    /**
     * Update an existing menu item by its ID.
     *
     * @param id the ID of the menu item to be updated
     * @param menuItem the updated menu item details
     * @return the updated menu item
     */
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem) {
        return menuItemService.updateMenuItem(id, menuItem);
    }

    /**
     * Delete a menu item by its ID.
     *
     * @param id the ID of the menu item to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable int id) {
        menuItemService.deleteMenuItem(id);
    }

    /**
     * Retrieve the average price of all menu items.
     *
     * @return the average price of all menu items
     */
    @GetMapping("/analysis/averagePrice")
    public double getAveragePrice() {
        return menuItemService.calculateAveragePrice();
    }

    /**
     * Retrieve the average price of menu items by category.
     *
     * @param categoryName the category name of the menu items
     * @return the average price of the menu items in the specified category
     */
    @GetMapping("/analysis/averagePrice/{categoryName}")
    public double getAveragePriceByCategory(@PathVariable String categoryName) {
        return menuItemService.calculateAveragePrice(categoryName);
    }

    /**
     * Retrieve the median price of all menu items.
     *
     * @return the median price of all menu items
     */
    @GetMapping("/analysis/medianPrice")
    public double getMedianPrice() {
        return menuItemService.calculateMedianPrice();
    }
}
