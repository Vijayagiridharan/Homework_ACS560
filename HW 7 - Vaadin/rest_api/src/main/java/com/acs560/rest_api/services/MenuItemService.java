package com.acs560.rest_api.services;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.entities.MenuItemEntity;
import java.util.List;

public interface MenuItemService {
    List<MenuItemEntity> getMenuItems();
    MenuItemEntity addMenuItem(MenuItemEntity menuItem);
   
	MenuItemEntity getMenuItem(Long id);
	void updateMenuItem(Long id, MenuItemEntity menuItem);
	void deleteMenuItem(Long id);
	List<MenuItemEntity> getMenuItemsByCategory(CategoryEntity category);
}
