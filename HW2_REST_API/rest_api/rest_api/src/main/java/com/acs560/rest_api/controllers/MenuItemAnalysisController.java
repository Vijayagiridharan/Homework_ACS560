package com.acs560.rest_api.controllers;

import com.acs560.rest_api.services.MenuItemAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that handles requests for menu item analysis, including
 * calculating the average price and calories of menu items.
 */
@RestController
@RequestMapping("/api/v1/menuItemsAnalysis")
public class MenuItemAnalysisController {

    @Autowired
    private MenuItemAnalysisService menuItemAnalysisService;

    /**
     * Endpoint to retrieve the average price of menu items.
     * 
     * @return the average price of all menu items as a double.
     */
    @GetMapping("/averagePrice")
    public double getAveragePrice() {
        return menuItemAnalysisService.calculateAveragePrice();
    }

    /**
     * Endpoint to retrieve the average calories of menu items.
     * 
     * @return the average calories of all menu items as a double.
     */
    @GetMapping("/averageCalories")
    public double getAverageCalories() {
        return menuItemAnalysisService.calculateAverageCalories();
    }
}