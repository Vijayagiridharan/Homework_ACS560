package com.acs560.rest_api.repositories;

import com.acs560.rest_api.models.MenuItem;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing {@link MenuItem} entities.
 * This class provides methods to read from and write to a CSV file,
 * as well as to add, update, and delete menu items.
 */
@Repository
public class MenuItemRepository {

    private List<MenuItem> menuItems;

    /**
     * Constructs a {@link MenuItemRepository} instance and initializes
     * the list of menu items from the CSV file.
     */
    public MenuItemRepository() {
        this.menuItems = initializeMenuItems();
    }

    /**
     * Initializes the list of menu items by reading from the CSV file.
     *
     * @return a list of {@link MenuItem} parsed from the CSV file.
     */
    private List<MenuItem> initializeMenuItems() {
        try (Reader reader = Files.newBufferedReader(Paths.get("menu_items.csv"))) {
            return new CsvToBeanBuilder<MenuItem>(reader)
                    .withType(MenuItem.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Saves the current list of menu items to the CSV file.
     *
     * @param append whether to append to the existing CSV file or overwrite it.
     * @return a message indicating the result of the save operation.
     */
    public String saveToCsv(boolean append) {
        try (FileWriter writer = new FileWriter("menu_items.csv", append)) {
            StatefulBeanToCsv<MenuItem> beanToCsv = new StatefulBeanToCsvBuilder<MenuItem>(writer).build();
            beanToCsv.write(menuItems);
            return "Data saved successfully to CSV file.";
        } catch (Exception e) {
            return "Failed to save data to CSV: " + e.getMessage();
        }
    }

    /**
     * Adds a new menu item to the repository.
     *
     * @param newItem the {@link MenuItem} to add.
     * @return a message indicating the result of the addition operation.
     */
    public String addMenuItem(MenuItem newItem) {
        if (menuItems.stream().anyMatch(item -> item.getItemId() == newItem.getItemId())) {
            return "Duplicate item. Item not added.";
        }
        menuItems.add(newItem);
        String saveResult = saveToCsv(true); // Append to CSV
        if (saveResult.startsWith("Failed")) {
            menuItems.remove(newItem); // Rollback if saving fails
            return saveResult; // Return the failure message
        }
        return "Menu item added successfully. " + saveResult;
    }

    /**
     * Updates an existing menu item in the repository.
     *
     * @param id          the ID of the item to update.
     * @param updatedItem the {@link MenuItem} with updated data.
     * @return a message indicating the result of the update operation.
     */
    public String updateMenuItem(int id, MenuItem updatedItem) {
        MenuItem existingItem = menuItems.stream()
                .filter(item -> item.getItemId() == id)
                .findFirst()
                .orElse(null);

        if (existingItem == null) {
            return "Item not found. Update failed.";
        }

        int index = menuItems.indexOf(existingItem);
        menuItems.set(index, updatedItem);
        String saveResult = saveToCsv(false); // Overwrite CSV
        if (saveResult.startsWith("Failed")) {
            menuItems.set(index, existingItem); // Rollback
            return saveResult; // Return the failure message
        }
        return "Menu item updated successfully. " + saveResult;
    }

    /**
     * Deletes a menu item from the repository.
     *
     * @param id the ID of the item to delete.
     * @return a message indicating the result of the deletion operation.
     */
    public String deleteMenuItem(int id) {
        MenuItem itemToDelete = menuItems.stream()
                .filter(item -> item.getItemId() == id)
                .findFirst()
                .orElse(null);

        if (itemToDelete == null) {
            return "Item not found. Deletion failed.";
        }

        menuItems.remove(itemToDelete);
        String saveResult = saveToCsv(false); // Overwrite CSV
        if (saveResult.startsWith("Failed")) {
            menuItems.add(itemToDelete); // Rollback
            return saveResult; // Return the failure message
        }
        return "Menu item deleted successfully. " + saveResult;
    }

    /**
     * Retrieves the list of all menu items.
     *
     * @return a list of {@link MenuItem}.
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the ID of the item to retrieve.
     * @return the {@link MenuItem} with the specified ID, or null if no such item exists.
     */
    public MenuItem getMenuItem(int id) {
        return menuItems.stream()
                .filter(item -> item.getItemId() == id)
                .findFirst()
                .orElse(null);
    }
}
