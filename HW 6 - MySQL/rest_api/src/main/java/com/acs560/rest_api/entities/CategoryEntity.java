package com.acs560.rest_api.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

/**
 * Represents a category entity in the database.
 * <p>
 * This class is mapped to the "CATEGORY_ENTITY" table and contains information
 * about a category, including its unique identifier, name, and associated menu items.
 * </p>
 */
@Entity
@Table(name = "CATEGORY_ENTITY")
public class CategoryEntity {

    /**
     * The unique identifier for the category.
     * This value is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    /**
     * The name of the category.
     * This field must be unique across all categories.
     */
    @Column(unique = true)
    private String categoryName;

    /**
     * The list of menu items associated with this category.
     * This establishes a one-to-many relationship with {@link MenuItemEntity}.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<MenuItemEntity> menuItems;

    /**
     * Gets the unique identifier of the category.
     *
     * @return the category ID.
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the unique identifier of the category.
     *
     * @param categoryId the category ID to set.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the name of the category.
     *
     * @return the category name.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the name of the category.
     *
     * @param categoryName the category name to set.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Gets the list of menu items associated with this category.
     *
     * @return the list of menu items.
     */
    public List<MenuItemEntity> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets the list of menu items associated with this category.
     *
     * @param menuItems the list of menu items to set.
     */
    public void setMenuItems(List<MenuItemEntity> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * Sets the ID of the category.
     * <p>
     * This method may be used internally and is not typically exposed for public use.
     * </p>
     *
     * @param id the ID to set.
     */
    public void setId(int id) {
        // TODO Auto-generated method stub
    }
}
