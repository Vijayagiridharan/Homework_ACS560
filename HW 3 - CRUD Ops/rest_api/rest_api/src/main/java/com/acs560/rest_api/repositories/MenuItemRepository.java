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
     * @return true if the data was successfully saved, false otherwise.
     * @throws Exception if an error occurs while saving data to the CSV file.
     */
    public boolean saveToCsv(boolean append) throws Exception {
        try (FileWriter writer = new FileWriter("menu_items.csv", append)) {
            StatefulBeanToCsv<MenuItem> beanToCsv = new StatefulBeanToCsvBuilder<MenuItem>(writer).build();
            beanToCsv.write(menuItems);
            return true;
        } catch (Exception e) {
            throw new Exception("Failed to save data to CSV", e);
        }
    }

    /**
     * Adds a new menu item to the repository.
     *
     * @param newItem the {@link MenuItem} to add.
     * @return true if the item was successfully added, false if the item already exists.
     * @throws Exception if an error occurs while saving data to the CSV file.
     */
    public boolean addMenuItem(MenuItem newItem) throws Exception {
        if (menuItems.stream().anyMatch(item -> item.getItemId() == newItem.getItemId())) {
            return false; // Duplicate item
        }
        menuItems.add(newItem);

        if (!saveToCsv(true)) { // Append to CSV
            menuItems.remove(newItem); // Rollback if saving fails
            return false;
        }
        return true;
    }

    /**
     * Updates an existing menu item in the repository.
     *
     * @param id the ID of the item to update.
     * @param updatedItem the {@link MenuItem} with updated data.
     * @return true if the item was successfully updated, false if the item does not exist.
     * @throws Exception if an error occurs while saving data to the CSV file.
     */
    public boolean updateMenuItem(int id, MenuItem updatedItem) throws Exception {
        MenuItem existingItem = menuItems.stream()
                .filter(item -> item.getItemId() == id)
                .findFirst()
                .orElse(null);

        if (existingItem == null) {
            return false; // Data does not exist
        }

        int index = menuItems.indexOf(existingItem);
        menuItems.set(index, updatedItem);

        if (!saveToCsv(false)) { // Overwrite CSV
            menuItems.set(index, existingItem); // Rollback
            return false;
        }
        return true;
    }

    /**
     * Deletes a menu item from the repository.
     *
     * @param id the ID of the item to delete.
     * @return true if the item was successfully deleted, false if the item does not exist.
     * @throws Exception if an error occurs while saving data to the CSV file.
     */
    public boolean deleteMenuItem(int id) throws Exception {
        MenuItem itemToDelete = menuItems.stream()
                .filter(item -> item.getItemId() == id)
                .findFirst()
                .orElse(null);

        if (itemToDelete == null) {
            return false; // Data does not exist
        }

        menuItems.remove(itemToDelete);

        if (!saveToCsv(false)) { // Overwrite CSV
            menuItems.add(itemToDelete); // Rollback
            return false;
        }
        return true;
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
