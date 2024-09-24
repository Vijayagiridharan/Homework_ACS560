package com.acs560.rest_api.services.impl;

import com.acs560.rest_api.models.MenuItem;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.services.MenuItemAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link MenuItemAnalysisService} interface that provides
 * methods for analyzing menu items.
 * <p>
 * This service calculates average price and average calories of menu items
 * retrieved from the {@link MenuItemRepository}.
 * </p>
 */
@Service
public class MenuItemAnalysisServiceImpl implements MenuItemAnalysisService {

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemAnalysisServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Calculates the average price of menu items.
     * <p>
     * This method retrieves all menu items from the {@link MenuItemRepository},
     * maps each item to its price, computes the average of these prices, and
     * returns the result. If no menu items are available, it returns 0.0.
     * </p>
     * 
     * @return the average price of menu items, or 0.0 if there are no menu items.
     */
    @Override
    public double calculateAveragePrice() {
        List<MenuItem> menuItems = menuItemRepository.getMenuItems();  // Use injected repository
        return menuItems.stream()
            .mapToDouble(MenuItem::getPrice)
            .average()
            .orElse(0.0);
    }

    /**
     * Calculates the average calories of menu items.
     * <p>
     * This method retrieves all menu items from the {@link MenuItemRepository},
     * maps each item to its calorie count, computes the average of these calorie
     * counts, and returns the result. If no menu items are available, it returns 0.0.
     * </p>
     * 
     * @return the average calories of menu items, or 0.0 if there are no menu items.
     */
    @Override
    public double calculateAverageCalories() {
        List<MenuItem> menuItems = menuItemRepository.getMenuItems();  // Use injected repository
        return menuItems.stream()
            .mapToDouble(MenuItem::getCalories)
            .average()
            .orElse(0.0);
    }
}
