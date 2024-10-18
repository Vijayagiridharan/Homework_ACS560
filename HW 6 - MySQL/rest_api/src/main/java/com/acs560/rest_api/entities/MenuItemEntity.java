package com.acs560.rest_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Represents a menu item in the restaurant's menu.
 * This entity class maps to the MENU_ITEM_ENTITY table in the database.
 * It contains various attributes related to the menu item, including
 * its name, category, price, calories, preparation time, and more.
 */
@Entity
@Table(name = "MENU_ITEM_ENTITY")
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    private String itemName;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category; // FK relationship to CategoryEntity

    private BigDecimal price;
    private int calories;
    private int preparationTime;
    private String cuisineType;
    private boolean vegetarian;
    private int spicyLevel;
    private boolean availability;
    private String description;

    /**
     * Gets the ID of the menu item.
     *
     * @return the item ID.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the ID of the menu item.
     *
     * @param itemId the item ID to set.
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the name of the menu item.
     *
     * @return the item name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the menu item.
     *
     * @param itemName the item name to set.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the category of the menu item.
     *
     * @return the category entity associated with this menu item.
     */
    public CategoryEntity getCategory() {
        return category;
    }

    /**
     * Sets the category of the menu item.
     *
     * @param category the category entity to set.
     */
    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    /**
     * Gets the price of the menu item.
     *
     * @return the price as a BigDecimal.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price the price to set as a BigDecimal.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the calorie content of the menu item.
     *
     * @return the number of calories.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets the calorie content of the menu item.
     *
     * @param calories the number of calories to set.
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Gets the preparation time of the menu item in minutes.
     *
     * @return the preparation time.
     */
    public int getPreparationTime() {
        return preparationTime;
    }

    /**
     * Sets the preparation time of the menu item.
     *
     * @param preparationTime the preparation time to set in minutes.
     */
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    /**
     * Gets the cuisine type of the menu item.
     *
     * @return the cuisine type as a String.
     */
    public String getCuisineType() {
        return cuisineType;
    }

    /**
     * Sets the cuisine type of the menu item.
     *
     * @param cuisineType the cuisine type to set.
     */
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    /**
     * Checks if the menu item is vegetarian.
     *
     * @return true if the menu item is vegetarian, false otherwise.
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * Sets whether the menu item is vegetarian.
     *
     * @param vegetarian true if the menu item is vegetarian, false otherwise.
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * Gets the spicy level of the menu item.
     *
     * @return the spicy level as an integer.
     */
    public int getSpicyLevel() {
        return spicyLevel;
    }

    /**
     * Sets the spicy level of the menu item.
     *
     * @param spicyLevel the spicy level to set.
     */
    public void setSpicyLevel(int spicyLevel) {
        this.spicyLevel = spicyLevel;
    }

    /**
     * Checks if the menu item is available.
     *
     * @return true if the menu item is available, false otherwise.
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * Sets the availability of the menu item.
     *
     * @param availability true if the menu item is available, false otherwise.
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    /**
     * Gets the description of the menu item.
     *
     * @return the description as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the menu item.
     *
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
