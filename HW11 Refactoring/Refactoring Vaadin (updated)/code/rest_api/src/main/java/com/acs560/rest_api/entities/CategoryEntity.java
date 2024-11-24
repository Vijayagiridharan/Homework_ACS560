package com.acs560.rest_api.entities;

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents a category entity in the database.
 * This class is mapped to the "CATEGORY_ENTITY" table and contains information
 * about a category, including its unique identifier, name, and associated menu items.
 */
@Entity
@Table(name = "CATEGORY_ENTITY")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    
    @Column(unique = true, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemEntity> menuItems;

    // Getters and setters

    public CategoryEntity() {
        // Default constructor
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<MenuItemEntity> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemEntity> menuItems) {
        this.menuItems = menuItems;
    }
    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

}
