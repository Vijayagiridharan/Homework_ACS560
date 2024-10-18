package com.acs560.rest_api.controllers;

import com.acs560.rest_api.models.MenuItem;
import com.acs560.rest_api.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to {@link MenuItem} entities.
 */
@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the unique identifier of the menu item
     * @return ResponseEntity containing the requested {@link MenuItem} or a 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable int id) {
        MenuItem item = menuItemService.getMenuItem(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves a list of all menu items.
     *
     * @return a list of {@link MenuItem} objects
     */
    @GetMapping
    public List<MenuItem> getMenuItems() {
        return menuItemService.getMenuItems();
    }

    /**
     * Adds a new menu item.
     *
     * @param menuItem the {@link MenuItem} object to be added
     * @return ResponseEntity containing a message about the addition result
     */
    @PostMapping
    public ResponseEntity<String> addMenuItem(@RequestBody MenuItem menuItem) {
        String response = menuItemService.addMenuItem(menuItem);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing menu item.
     *
     * @param id the unique identifier of the menu item to be updated
     * @param menuItem the updated {@link MenuItem} information
     * @return ResponseEntity containing a message about the update result
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem) {
        String response = menuItemService.updateMenuItem(id, menuItem);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a menu item by its ID.
     *
     * @param id the unique identifier of the menu item to be deleted
     * @return ResponseEntity containing a message about the deletion result
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable int id) {
        String response = menuItemService.deleteMenuItem(id);
        return ResponseEntity.ok(response);
    }
}
