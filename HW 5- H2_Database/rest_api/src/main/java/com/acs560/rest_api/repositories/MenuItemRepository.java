package com.acs560.rest_api.repositories;

import com.acs560.rest_api.entities.MenuItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link MenuItemEntity} entities.
 * This interface extends {@link CrudRepository} to provide CRUD operations.
 */
@Repository
public interface MenuItemRepository extends CrudRepository<MenuItemEntity, Integer> {

    /**
     * Retrieves a list of menu items that belong to the specified category.
     * 
     * @param categoryName the name of the category to filter menu items by.
     * @return a List of {@link MenuItemEntity} objects that belong to the specified category.
     */
    List<MenuItemEntity> findByCategoryNameIgnoreCase(String categoryName);

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
}
