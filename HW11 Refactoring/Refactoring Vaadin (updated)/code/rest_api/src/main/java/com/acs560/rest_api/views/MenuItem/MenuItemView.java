package com.acs560.rest_api.views.MenuItem;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.services.MenuItemService;
import com.acs560.rest_api.services.CategoryService;
import com.acs560.rest_api.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "menu-items", layout = MainLayout.class)
@PageTitle("Menu Items | rest_api")
public class MenuItemView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    private final MenuItemService menuItemService;
    private final CategoryService categoryService;
    private final Grid<MenuItemEntity> grid = new Grid<>(MenuItemEntity.class);
    private TextField filterText = new TextField();

    @Autowired
    public MenuItemView(MenuItemService menuItemService, CategoryService categoryService) {
        this.menuItemService = menuItemService;
        this.categoryService = categoryService;
        
        configureGrid();
        add(filterText, grid, createAddButton());
        updateGrid();
    }

    private void configureGrid() {
        grid.setColumns("itemName", "price", "calories", "preparationTime", "cuisineType", 
                        "vegetarian", "spicyLevel", "availability", "description", "category.categoryName");

        filterText.setPlaceholder("Filter by item name...");
        filterText.setClearButtonVisible(true);
        filterText.addValueChangeListener(e -> updateGrid());

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                openFormDialog(event.getValue());
            }
        });

        grid.addComponentColumn(item -> {
            Button deleteButton = new Button("Delete", click -> deleteMenuItem(item));
            return deleteButton;
        });
    }

   
    private Button createAddButton() {
        return new Button("Add Menu Item", click -> openFormDialog(new MenuItemEntity()));
    }


    private void openFormDialog(MenuItemEntity menuItem) {
    	
    	if (menuItem.getCategory() == null) {
            menuItem.setCategory(new CategoryEntity()); // Set a new default CategoryEntity if needed
        }

        MenuItemForm form = new MenuItemForm(categoryService, menuItem);
        Dialog dialog = new Dialog(form);

        form.addSaveListener(e -> {
            if (menuItem.getItemId() == null) {
                menuItemService.addMenuItem(e.getMenuItem());
            } else {
                menuItemService.updateMenuItem(menuItem.getItemId(), e.getMenuItem());
            }
            dialog.close();
            updateGrid();
        });

        form.addDeleteListener(e -> {
            menuItemService.deleteMenuItem(menuItem.getItemId());
            dialog.close();
            updateGrid();
        });

        form.addCloseListener(e -> dialog.close());

        dialog.open();
    }

    private void deleteMenuItem(MenuItemEntity menuItem) {
        menuItemService.deleteMenuItem(menuItem.getItemId());
        updateGrid();
    }

    private void updateGrid() {
        List<MenuItemEntity> filteredItems = menuItemService.getMenuItems().stream()
                .filter(item -> item.getItemName().toLowerCase().contains(filterText.getValue().toLowerCase()))
                .collect(Collectors.toList());
        grid.setItems(filteredItems);
    }
}
