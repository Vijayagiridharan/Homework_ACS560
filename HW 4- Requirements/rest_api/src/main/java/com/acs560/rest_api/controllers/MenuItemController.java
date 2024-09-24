package com.acs560.rest_api.controllers;

import com.acs560.rest_api.models.MenuItem;
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * Retrieves a menu item by its ID.
     * 
     * @param id The ID of the menu item to retrieve.
     * @return A ResponseEntity containing the menu item and HTTP status 200 (OK) if found,
     *         or HTTP status 404 (Not Found) if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable int id) {
        MenuItem menuItem = menuItemService.getMenuItem(id);
        return menuItem != null ? ResponseEntity.ok(menuItem) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Retrieves all menu items.
     * 
     * @return A ResponseEntity containing a list of menu items and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<MenuItem>> getMenuItems() {
        return ResponseEntity.ok(menuItemService.getMenuItems());
    }

    /**
     * Creates a new menu item.
     * 
     * @param menuItem The menu item to create.
     * @return A ResponseEntity containing a success message and HTTP status 201 (Created)
     *         if creation is successful, or HTTP status 500 (Internal Server Error) if an error occurs.
     */
    @PostMapping
    public ResponseEntity<String> createMenuItem(@RequestBody MenuItem menuItem) {
        try {
            menuItemService.addMenuItem(menuItem);
            return ResponseEntity.status(HttpStatus.CREATED).body("MenuItem created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating MenuItem");
        }
    }
   
    /**
     * Updates an existing menu item.
     * 
     * @param id The ID of the menu item to update.
     * @param updatedMenuItem The updated menu item data.
     * @return A ResponseEntity containing a success message and HTTP status 200 (OK) if the update is successful,
     *         HTTP status 404 (Not Found) if the menu item is not found, or HTTP status 500 (Internal Server Error) if an error occurs.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenuItem(@PathVariable("id") int id, @RequestBody MenuItem updatedMenuItem) {
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
     * Deletes a menu item by its ID.
     * 
     * @param id The ID of the menu item to delete.
     * @return A ResponseEntity containing a success message and HTTP status 200 (OK) if the deletion is successful,
     *         HTTP status 404 (Not Found) if the menu item is not found, or HTTP status 500 (Internal Server Error) if an error occurs.
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
     * @return A ResponseEntity containing the median price and HTTP status 200 (OK),
     *         or HTTP status 500 (Internal Server Error) if an error occurs.
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
     * Retrieves a list of menu items filtered by the specified category.
     *
     * This method handles GET requests to the endpoint "/category/{categoryName}". 
     * It interacts with the MenuItemService to fetch the menu items that match 
     * the provided category name. If no menu items are found for the given 
     * category, a 404 Not Found response is returned. Otherwise, a 200 OK 
     * response is returned along with the list of matching menu items.
     *
     * @param categoryName the name of the category for which menu items are to be retrieved
     * @return a ResponseEntity containing a list of MenuItem objects if found; 
     *         otherwise, a ResponseEntity with a NOT_FOUND status
     */
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String categoryName) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByCategory(categoryName);
        if (menuItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(menuItems);
        }
        return ResponseEntity.ok(menuItems);
    }
}
