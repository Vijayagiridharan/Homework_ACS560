package com.acs560.rest_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a menu item entity for the restaurant's menu.
 * This class maps to the MENU_ITEM_ENTITY table in the database.
 * Each instance of this class corresponds to a single menu item.
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "MENU_ITEM_ENTITY")  // Use uppercase to match the database table name
public class MenuItemEntity {

    /**
     * Unique identifier for the menu item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")  // Specify the column name for itemId
    private int itemId;
    
    /**
     * Name of the menu item.
     */
    @Column(name = "ITEM_NAME")  // Specify the column name for itemName
    private String itemName;

    /**
     * Identifier for the category the menu item belongs to.
     */
    @Column(name = "CATEGORY_ID")  // Specify the column name for categoryId
    private int categoryId;

    /**
     * Price of the menu item.
     * Stored as BigDecimal for precision.
     */
    @Column(name = "PRICE")  // Specify the column name for price
    private BigDecimal price;  // Change from double to BigDecimal

    /**
     * Caloric value of the menu item.
     */
    @Column(name = "CALORIES")  // Specify the column name for calories
    private int calories;

    /**
     * Preparation time for the menu item, in minutes.
     */
    @Column(name = "PREPARATION_TIME")  // Specify the column name for preparationTime
    private int preparationTime;

    /**
     * Type of cuisine for the menu item (e.g., Italian, Chinese).
     */
    @Column(name = "CUISINE_TYPE")  // Specify the column name for cuisineType
    private String cuisineType;

    /**
     * Indicates whether the menu item is vegetarian.
     */
    @Column(name = "VEGETARIAN")  // Specify the column name for vegetarian
    private boolean vegetarian;

    /**
     * Spiciness level of the menu item.
     */
    @Column(name = "SPICY_LEVEL")  // Specify the column name for spicyLevel
    private int spicyLevel;

    /**
     * Indicates whether the menu item is currently available.
     */
    @Column(name = "AVAILABILITY")  // Specify the column name for availability
    private boolean availability;

    /**
     * Name of the category the menu item belongs to.
     */
    @Column(name = "CATEGORY_NAME")  // Specify the column name for categoryName
    private String categoryName;

    /**
     * Description of the menu item.
     */
    @Column(name = "DESCRIPTION")  // Specify the column name for description
    private String description;

    // Getters and setters

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getSpicyLevel() {
        return spicyLevel;
    }

    public void setSpicyLevel(int spicyLevel) {
        this.spicyLevel = spicyLevel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
