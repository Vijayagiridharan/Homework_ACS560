package com.acs560.rest_api.repositories;

import com.acs560.rest_api.models.MenuItem;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing {@link MenuItem} data.
 * It reads data from a CSV file and provides methods to retrieve the menu items.
 */
public class MenuItemRepository {

    private static List<MenuItem> menuItems;

    /**
     * Retrieves the list of all {@link MenuItem}s.
     * If the list has not been initialized, it will be loaded from a CSV file.
     *
     * @return a {@link List} of {@link MenuItem}s
     */
    public static List<MenuItem> getMenuItems() {
        if (menuItems == null) {
            menuItems = initializeMenuItems();
        }
        return menuItems;
    }

    /**
     * Initializes the menu items by reading them from a CSV file and converting them
     * into {@link MenuItem} objects. The CSV data is parsed using the OpenCSV library.
     * If an error occurs during file reading, it returns an empty list.
     *
     * @return a {@link List} of {@link MenuItem}s, or an empty list in case of an error
     */
    private static List<MenuItem> initializeMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        try (var reader = new FileReader(Paths.get("menu_items.csv").toFile())) {
            // Set up CsvToBean to map CSV data to MenuItem objects
            CsvToBean<MenuItem> csvToBean = new CsvToBeanBuilder<MenuItem>(reader)
                    .withType(MenuItem.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    // .withSkipLines(1)  // Uncomment if CSV has a header row
                    .build();

            // Parse the CSV file into a list of MenuItem objects
            menuItems = csvToBean.parse();

            // Debug output of parsed menu item IDs
            System.out.println("CSV Data Parsed Successfully:");
            for (MenuItem item : menuItems) {
                System.out.println(item.getItemId());
            }
        } catch (IOException e) {
            e.printStackTrace();  // Optionally log the error
        }

        return menuItems;
    }
}

