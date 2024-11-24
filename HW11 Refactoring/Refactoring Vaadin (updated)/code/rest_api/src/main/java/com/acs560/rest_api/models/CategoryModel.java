package com.acs560.rest_api.models;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the Category.
 */
public class CategoryModel {

    private Integer categoryId;
    private String categoryName;
    private List<MenuItemModel> menuItems; // You can include MenuItemModel if needed

    // Getters and setters

    public CategoryModel() {
        // Default constructor
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<MenuItemModel> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemModel> menuItems) {
        this.menuItems = menuItems;
    }
}

