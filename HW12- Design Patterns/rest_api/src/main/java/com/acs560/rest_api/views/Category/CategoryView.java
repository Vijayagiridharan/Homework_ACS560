package com.acs560.rest_api.views.Category;

import com.acs560.rest_api.entities.CategoryEntity;
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
@Route(value = "categories", layout = MainLayout.class)
@PageTitle("Categories | rest_api")
public class CategoryView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    private final CategoryService categoryService;
    private final Grid<CategoryEntity> grid = new Grid<>(CategoryEntity.class);
    private TextField filterText = new TextField();

    @Autowired
    public CategoryView(CategoryService categoryService) {
        this.categoryService = categoryService;
        configureGrid();
        add(filterText, grid, createAddButton());
        updateGrid();
    }

    private void configureGrid() {
        grid.setColumns("categoryName");
        grid.setItems(categoryService.getCategories());

        // Add filtering functionality
        filterText.setPlaceholder("Filter by category name...");
        filterText.setClearButtonVisible(true);
        filterText.addValueChangeListener(e -> updateGrid());

        // Add delete button to grid
        grid.addComponentColumn(category -> {
            Button deleteButton = new Button("Delete", event -> deleteCategory(category));
            return deleteButton;
        }).setHeader("Actions");

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                openFormDialog(event.getValue());
            }
        });
    }

    private Button createAddButton() {
        return new Button("Add Category", click -> openFormDialog(new CategoryEntity()));
    }

    private void openFormDialog(CategoryEntity category) {
        CategoryForm form = new CategoryForm();
        Dialog dialog = new Dialog(form);
        form.getCategoryNameField().setValue(category.getCategoryName() == null ? "" : category.getCategoryName());

        form.addSaveListener(e -> {
            if (category.getCategoryId() == null) {
                try {
                    categoryService.addCategory(e.getCategory());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    categoryService.updateCategory(category.getCategoryId(), e.getCategory());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            dialog.close();
            updateGrid();
        });

        form.addCloseListener(e -> dialog.close());
        dialog.open();
    }

    private void updateGrid() {
        List<CategoryEntity> filteredCategories = categoryService.getCategories()
                .stream()
                .filter(category -> category.getCategoryName()
                        .toLowerCase()
                        .contains(filterText.getValue().toLowerCase()))
                .collect(Collectors.toList());

        grid.setItems(filteredCategories);
    }

    private void deleteCategory(CategoryEntity category) {
        try {
            categoryService.deleteCategory(category.getCategoryId());
            updateGrid();  // Refresh grid after deletion
        } catch (Exception e) {
            e.printStackTrace();  // Handle deletion failure (e.g., show a notification or error message)
        }
    }
}
