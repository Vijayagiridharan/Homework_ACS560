package com.acs560.rest_api.views;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.services.CategoryService;
import com.acs560.rest_api.services.MenuItemService;
import com.acs560.rest_api.views.Category.CategoryForm;
import com.acs560.rest_api.views.MenuItem.MenuItemForm;

public class FormFactory {

    private CategoryService categoryService;
    private MenuItemService menuItemService;

    public FormFactory(CategoryService categoryService, MenuItemService menuItemService) {
        this.categoryService = categoryService;
        this.setMenuItemService(menuItemService);
    }

    // Factory method to create MenuItemForm
    public MenuItemForm createMenuItemForm(MenuItemEntity menuItem) {
        return new MenuItemForm(categoryService, menuItem);
    }

    // Factory method to create CategoryForm (if needed)
    public CategoryForm createCategoryForm(CategoryEntity category) {
        return new CategoryForm(categoryService, category);
    }

	public MenuItemService getMenuItemService() {
		return menuItemService;
	}

	public void setMenuItemService(MenuItemService menuItemService) {
		this.menuItemService = menuItemService;
	}
}
