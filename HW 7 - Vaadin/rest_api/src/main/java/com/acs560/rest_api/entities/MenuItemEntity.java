package com.acs560.rest_api.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Represents a menu item in the restaurant's menu.
 * This entity class maps to the MENU_ITEM_ENTITY table in the database.
 * It contains various attributes related to the menu item, including
 * its name, category, price, calories, preparation time, and more.
 */
@Entity
@Table(name = "MENU_ITEM_ENTITY") // Ensure this matches your actual table name
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category; // FK relationship to CategoryEntity

    @Column(nullable = false)
    private BigDecimal price;

    private int calories;

    @Column(name = "preparation_time", nullable = false) // Adjusted to match DB column name
    private int preparationTime;

    @Column(nullable = false)
    private String cuisineType;

    private boolean vegetarian;

    @Column(nullable = false)
    private int spicyLevel;

    private boolean availability;

    @Column(length = 500)
    private String description;

    // Default constructor
    public MenuItemEntity() {
        // Default constructor
    }

    // Parameterized constructor
    public MenuItemEntity(String itemName, BigDecimal price, int calories, int preparationTime, String cuisineType, boolean vegetarian, int spicyLevel, boolean availability, String description) {
        this.itemName = itemName;
        this.price = price;
        this.calories = calories;
        this.preparationTime = preparationTime;
        this.cuisineType = cuisineType;
        this.vegetarian = vegetarian;
        this.spicyLevel = spicyLevel;
        this.availability = availability;
        this.description = description;
    }
 // Add this constructor
    public MenuItemEntity(String itemName, BigDecimal price) {
        this.itemName = itemName;
        this.price = price;
    }


    // Getters and Setters (as before)
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getSpicyLevel() {
        return spicyLevel;
    }

    public void setSpicyLevel(int spicyLevel) {
        this.spicyLevel = spicyLevel;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
