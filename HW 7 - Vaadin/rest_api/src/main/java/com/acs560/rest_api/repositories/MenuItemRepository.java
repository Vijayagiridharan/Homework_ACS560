package com.acs560.rest_api.repositories;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.entities.MenuItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link MenuItemEntity} entities.
 * This interface extends {@link CrudRepository} to provide CRUD operations.
 */
@Repository
public interface MenuItemRepository extends CrudRepository<MenuItemEntity, Long> {

    /**
     * Retrieves a list of menu items that belong to the specified category name.
     * 
     * @param categoryName the name of the category to filter menu items by.
     * @return a List of {@link MenuItemEntity} objects that belong to the specified category.
     */
    @Query("SELECT m FROM MenuItemEntity m WHERE LOWER(m.category.categoryName) = LOWER(:categoryName)")
    List<MenuItemEntity> findByCategoryName(@Param("categoryName") String categoryName);

    /**
     * Retrieves a menu item by its itemName.
     *
     * @param itemName the name of the menu item to retrieve.
     * @return an Optional of {@link MenuItemEntity} if found, otherwise empty.
     */
    Optional<MenuItemEntity> findByItemNameIgnoreCase(String itemName);
    
    /**
     * Checks if a menu item exists by its itemName.
     * 
     * @param itemName the name of the menu item.
     * @return true if a menu item with the given name exists, false otherwise.
     */
    boolean existsByItemNameIgnoreCase(String itemName);

    /**
     * Retrieves a list of menu items by their associated category entity.
     *
     * @param category the category entity to filter menu items.
     * @return a List of {@link MenuItemEntity} objects that belong to the specified category entity.
     */
    @Query("SELECT m FROM MenuItemEntity m JOIN FETCH m.category WHERE m.category = :category")
    List<MenuItemEntity> findByCategory(@Param("category") CategoryEntity category);

    /**
     * Deletes a menu item by its ID.
     *
     * @param id the ID of the menu item to delete.
     */
    void deleteById(Long id);

    /**
     * Checks if a menu item exists by its ID.
     *
     * @param id the ID of the menu item.
     * @return true if a menu item with the given ID exists, false otherwise.
     */
    boolean existsById(Long id);

    /**
     * Retrieves a menu item by its ID.
     *
     * @param id the ID of the menu item to retrieve.
     * @return an Optional of {@link MenuItemEntity} if found, otherwise empty.
     */
    Optional<MenuItemEntity> findById(Long id);
}
