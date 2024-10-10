package com.acs560.rest_api.controllers;

import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing menu items in the REST API.
 * Provides endpoints for CRUD operations and additional functionality such as calculating median price.
 */
@RestController
@RequestMapping("/api/v1/menuItems")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    /**
     * Retrieves a specific menu item by its ID.
     *
     * @param id the ID of the menu item to retrieve
     * @return ResponseEntity containing the menu item if found, or a 404 Not Found status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemEntity> getMenuItem(@PathVariable int id) {
        Optional<MenuItemEntity> menuItemOpt = menuItemService.getMenuItem(id);
        return menuItemOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Retrieves all menu items.
     *
     * @return ResponseEntity containing a list of all menu items
     */
    @GetMapping
    public ResponseEntity<List<MenuItemEntity>> getMenuItems() {
        return ResponseEntity.ok(menuItemService.getMenuItems());
    }

    /**
     * Creates a new menu item.
     *
     * @param menuItem the menu item to create
     * @return ResponseEntity indicating the result of the creation operation
     */
    @PostMapping
    public ResponseEntity<String> createMenuItem(@RequestBody MenuItemEntity menuItem) {
        try {
            menuItemService.addMenuItem(menuItem);
            return ResponseEntity.status(HttpStatus.CREATED).body("MenuItem created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for more insight
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating MenuItem: " + e.getMessage());
        }
    }

    /**
     * Updates an existing menu item.
     *
     * @param id                the ID of the menu item to update
     * @param updatedMenuItem   the new data for the menu item
     * @return ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenuItem(@PathVariable("id") int id, @RequestBody MenuItemEntity updatedMenuItem) {
        try {
            boolean updated = menuItemService.updateMenuItem(id, updatedMenuItem);
            if (updated) {
                return ResponseEntity.ok("MenuItem updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MenuItem not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating MenuItem");
        }
    }

    /**
     * Deletes a specific menu item by its ID.
     *
     * @param id the ID of the menu item to delete
     * @return ResponseEntity indicating the result of the deletion operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable("id") int id) {
        try {
            boolean deleted = menuItemService.deleteMenuItem(id);
            if (deleted) {
                return ResponseEntity.ok("MenuItem deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MenuItem not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting MenuItem");
        }
    }

    /**
     * Calculates the median price of all menu items.
     *
     * @return ResponseEntity containing the median price or a 500 Internal Server Error status if an error occurs
     */
    @GetMapping("/medianPrice")
    public ResponseEntity<Double> getMedianPrice() {
        try {
            double medianPrice = menuItemService.calculateMedianPrice();
            return ResponseEntity.ok(medianPrice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves menu items by their category.
     *
     * @param categoryName the name of the category to filter menu items
     * @return ResponseEntity containing the list of menu items in the specified category or a 404 Not Found status if none are found
     */
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<MenuItemEntity>> getMenuItemsByCategory(@PathVariable String categoryName) {
        List<MenuItemEntity> menuItems = menuItemService.getMenuItemsByCategory(categoryName);
        if (menuItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(menuItems);
        }
        return ResponseEntity.ok(menuItems);
    }
}
