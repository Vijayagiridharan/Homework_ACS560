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
     * Finds menu items by their category name, ignoring case.
     *
     * @param categoryName the name of the category
     * @return a list of {@link MenuItemEntity} objects that belong to the specified category
     */
    List<MenuItemEntity> findByCategoryNameIgnoreCase(String categoryName);

    /**
     * Finds a list of menu items by their item name.
     *
     * @param itemName the name of the menu item
     * @return a list of {@link MenuItemEntity} objects matching the specified name
     */
    List<MenuItemEntity> findByItemName(String itemName);
}
