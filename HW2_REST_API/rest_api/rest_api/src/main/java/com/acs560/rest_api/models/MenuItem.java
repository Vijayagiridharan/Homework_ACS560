package com.acs560.rest_api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Objects;

/**
 * The MenuItem class represents an item in a menu, containing various attributes such as
 * item ID, name, category, price, and more. It also includes methods to access and modify
 * these attributes, along with hashCode and equals implementations for comparing MenuItems.
 */
@Getter
@NoArgsConstructor
public class MenuItem {

    /**
     * The unique identifier for the menu item.
     */
    private int itemId;

    /**
     * The name of the menu item.
     */
    private String itemName;

    /**
     * The category ID to which the item belongs.
     */
    private int categoryId;

    /**
     * The price of the menu item.
     */
    private double price;

    /**
     * The number of calories in the menu item.
     */
    private int calories;

    /**
     * The time required to prepare the menu item, in minutes.
     */
    private int preparationTime;

    /**
     * The type of cuisine the menu item belongs to.
     */
    private String cuisineType;

    /**
     * Whether the menu item is vegetarian.
     */
    private boolean vegetarian;

    /**
     * The level of spiciness of the menu item.
     */
    private int spicyLevel;

    /**
     * The availability status of the menu item.
     */
    private boolean availability;

    /**
     * The name of the category the menu item belongs to.
     */
    private String categoryName;

    /**
     * A description of the menu item.
     */
    private String description;

    /**
     * Generates the hash code for a MenuItem object based on its itemId, itemName, and categoryId.
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, categoryId);
    }

    /**
     * Compares this MenuItem to another object for equality. The comparison is based on itemId, itemName, 
     * and categoryId.
     *
     * @param obj The object to compare to.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MenuItem other = (MenuItem) obj;
        return itemId == other.itemId && Objects.equals(itemName, other.itemName) && categoryId == other.categoryId;
    }

    // Getters and setters for various fields

    /**
     * Gets the price of the menu item.
     *
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price The new price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the number of calories in the menu item.
     *
     * @return The number of calories.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets the number of calories in the menu item.
     *
     * @param calories The new number of calories to set.
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Gets the preparation time of the menu item.
     *
     * @return The preparation time in minutes.
     */
    public int getPreparationTime() {
        return preparationTime;
    }

    /**
     * Sets the preparation time of the menu item.
     *
     * @param preparationTime The new preparation time in minutes.
     */
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    /**
     * Gets the cuisine type of the menu item.
     *
     * @return The cuisine type.
     */
    public String getCuisineType() {
        return cuisineType;
    }

    /**
     * Sets the cuisine type of the menu item.
     *
     * @param cuisineType The new cuisine type to set.
     */
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    /**
     * Checks if the menu item is vegetarian.
     *
     * @return {@code true} if the menu item is vegetarian; {@code false} otherwise.
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * Sets the vegetarian status of the menu item.
     *
     * @param vegetarian The new vegetarian status to set.
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * Gets the spiciness level of the menu item.
     *
     * @return The spiciness level.
     */
    public int getSpicyLevel() {
        return spicyLevel;
    }

    /**
     * Sets the spiciness level of the menu item.
     *
     * @param spicyLevel The new spiciness level to set.
     */
    public void setSpicyLevel(int spicyLevel) {
        this.spicyLevel = spicyLevel;
    }

    /**
     * Checks if the menu item is available.
     *
     * @return {@code true} if the menu item is available; {@code false} otherwise.
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * Sets the availability status of the menu item.
     *
     * @param availability The new availability status to set.
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    /**
     * Gets the category name of the menu item.
     *
     * @return The category name.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the category name of the menu item.
     *
     * @param categoryName The new category name to set.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Gets the description of the menu item.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the menu item.
     *
     * @param description The new description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the unique identifier of the menu item.
     *
     * @return The item ID.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the unique identifier of the menu item.
     *
     * @param itemId The new item ID to set.
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
