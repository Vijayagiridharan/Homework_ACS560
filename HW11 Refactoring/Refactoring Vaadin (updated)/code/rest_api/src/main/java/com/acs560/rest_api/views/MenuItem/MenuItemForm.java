package com.acs560.rest_api.views.MenuItem;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.services.CategoryService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;

import java.math.BigDecimal;

public class MenuItemForm extends FormLayout {

    private static final long serialVersionUID = 1L;

    private TextField itemName = new TextField("Item Name");
    private NumberField price = new NumberField("Price");
    private NumberField calories = new NumberField("Calories");
    private NumberField preparationTime = new NumberField("Preparation Time");
    private TextField cuisineType = new TextField("Cuisine Type");
    private Select<Boolean> vegetarian;
    private Select<Boolean> availability;
    private NumberField spicyLevel = new NumberField("Spicy Level");
    private TextField description = new TextField("Description");
    private Select<CategoryEntity> categorySelect = new Select<>();
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");

    private MenuItemEntity menuItem;

    public MenuItemForm(CategoryService categoryService, MenuItemEntity menuItem) {
        this.menuItem = menuItem;

        vegetarian = new Select<>();
        vegetarian.setLabel("Vegetarian");
        vegetarian.setItems(true, false);
        vegetarian.setRequiredIndicatorVisible(true);

        availability = new Select<>();
        availability.setLabel("Availability");
        availability.setItems(true, false);
        availability.setRequiredIndicatorVisible(true);

        categorySelect.setLabel("Category");
        categorySelect.setItems(categoryService.getCategories());
        categorySelect.setItemLabelGenerator(CategoryEntity::getCategoryName);
        categorySelect.setRequiredIndicatorVisible(true);

        itemName.setValue(menuItem.getItemName() == null ? "" : menuItem.getItemName());
        price.setValue(menuItem.getPrice() == null ? 0.0 : menuItem.getPrice().doubleValue());
        calories.setValue(menuItem.getCalories() == 0 ? 0.0 : menuItem.getCalories());
        preparationTime.setValue(menuItem.getPreparationTime() == 0 ? 0.0 : menuItem.getPreparationTime());
        cuisineType.setValue(menuItem.getCuisineType() == null ? "" : menuItem.getCuisineType());
        vegetarian.setValue(menuItem.isVegetarian());
        availability.setValue(menuItem.isAvailability());
        description.setValue(menuItem.getDescription() == null ? "" : menuItem.getDescription());
        spicyLevel.setValue(menuItem.getSpicyLevel() == 0 ? 0.0 : menuItem.getSpicyLevel());

        // Set default category if menuItem.getCategory() is null
        categorySelect.setValue(menuItem.getCategory() == null ? new CategoryEntity() : menuItem.getCategory());

        add(itemName, price, calories, preparationTime, cuisineType, vegetarian, spicyLevel, availability, description, categorySelect, createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addClickListener(event -> {
            // Convert the Double values to the correct types before setting them
            menuItem.setItemName(itemName.getValue());
            menuItem.setPrice(BigDecimal.valueOf(price.getValue())); // Convert double to BigDecimal
            menuItem.setCalories(calories.getValue().intValue()); // Convert Double to int
            menuItem.setPreparationTime(preparationTime.getValue().intValue()); // Convert Double to int
            menuItem.setCuisineType(cuisineType.getValue());
            menuItem.setVegetarian(vegetarian.getValue());
            menuItem.setAvailability(availability.getValue());
            menuItem.setDescription(description.getValue());
            menuItem.setSpicyLevel(spicyLevel.getValue().intValue()); // Convert Double to int
            menuItem.setCategory(categorySelect.getValue());

            fireEvent(new SaveEvent(this, menuItem)); // Fire SaveEvent with updated menuItem
        });
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this)));

        return new FormLayout(save, cancel, delete);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    // Custom Event Classes for Save, Close, Delete
    public static class SaveEvent extends ComponentEvent<MenuItemForm> {
        private static final long serialVersionUID = 1L;
        private final MenuItemEntity menuItem;

        public SaveEvent(MenuItemForm source, MenuItemEntity menuItem) {
            super(source, false);
            this.menuItem = menuItem;
        }

        public MenuItemEntity getMenuItem() {
            return menuItem;
        }
    }

    public static class CloseEvent extends ComponentEvent<MenuItemForm> {
        private static final long serialVersionUID = 1L;

        public CloseEvent(MenuItemForm source) {
            super(source, false);
        }
    }

    public static class DeleteEvent extends ComponentEvent<MenuItemForm> {
        private static final long serialVersionUID = 1L;

        public DeleteEvent(MenuItemForm source) {
            super(source, false);
        }
    }
}
