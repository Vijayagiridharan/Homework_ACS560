package com.acs560.rest_api.views.Category;

import com.acs560.rest_api.entities.CategoryEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;


public class CategoryForm extends FormLayout {

    private static final long serialVersionUID = 1L;

    private TextField categoryName = new TextField("Category Name");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");

    public CategoryForm() {
        add(categoryName, createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addClickListener(event -> {
            if (categoryName.getValue().isEmpty()) {
                categoryName.setInvalid(true);
                categoryName.setErrorMessage("Category name cannot be empty");
            } else {
                categoryName.setInvalid(false); // Clear validation
                fireEvent(new SaveEvent(this, new CategoryEntity(categoryName.getValue())));
            }
        });
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new FormLayout(save, cancel);
    }

    public TextField getCategoryNameField() {
        return categoryName;
    }

    public static abstract class CategoryFormEvent extends ComponentEvent<CategoryForm> {
        private static final long serialVersionUID = 1L;
        private final CategoryEntity category;

        protected CategoryFormEvent(CategoryForm source, CategoryEntity category) {
            super(source, false);
            this.category = category;
        }

        public CategoryEntity getCategory() {
            return category;
        }
    }

    public static class SaveEvent extends CategoryFormEvent {
        private static final long serialVersionUID = 1L;

        SaveEvent(CategoryForm source, CategoryEntity category) {
            super(source, category);
        }
    }

    public static class CloseEvent extends CategoryFormEvent {
        private static final long serialVersionUID = 1L;

        CloseEvent(CategoryForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
