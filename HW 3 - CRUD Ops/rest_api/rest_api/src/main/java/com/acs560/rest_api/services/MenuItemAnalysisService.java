package com.acs560.rest_api.services;

/**
 * The {@code MenuItemAnalysisService} interface provides methods for analyzing menu items.
 * Implementations of this interface should provide the logic to calculate specific metrics
 * related to menu items, such as average price and average calories.
 */
public interface MenuItemAnalysisService {

    /**
     * Calculates the average price of menu items.
     *
     * @return the average price of menu items as a {@code double}.
     */
    double calculateAveragePrice();

    /**
     * Calculates the average number of calories of menu items.
     *
     * @return the average number of calories of menu items as a {@code double}.
     */
    double calculateAverageCalories();
}
