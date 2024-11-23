package com.acs560.rest_api;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.exception.CustomNotFoundException;
import com.acs560.rest_api.repositories.CategoryRepository;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.services.impl.MenuItemServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuItemServiceImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    private CategoryEntity validCategory;
    private MenuItemEntity menuItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validCategory = new CategoryEntity();
        validCategory.setCategoryId(1L);
        validCategory.setCategoryName("Valid Category");

        menuItem = new MenuItemEntity();
        menuItem.setItemId(1L);
        menuItem.setItemName("Valid MenuItem");
        menuItem.setCategory(validCategory);
    }

    @Test
    void addMenuItem_WithValidCategory_ShouldSaveMenuItem() {
        // Arrange
        when(categoryRepository.findById(validCategory.getCategoryId())).thenReturn(Optional.of(validCategory));
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);

        // Act
        MenuItemEntity savedMenuItem = menuItemService.addMenuItem(menuItem);

        // Assert
        assertNotNull(savedMenuItem);
        assertEquals(menuItem.getItemName(), savedMenuItem.getItemName());
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    void addMenuItem_WithInvalidCategory_ShouldThrowException() {
        // Arrange
        when(categoryRepository.findById(validCategory.getCategoryId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomNotFoundException.class, () -> menuItemService.addMenuItem(menuItem));
        verify(menuItemRepository, never()).save(any(MenuItemEntity.class));
    }

    @Test
    void addMenuItem_WithNullCategory_ShouldThrowException() {
        // Arrange
        menuItem.setCategory(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> menuItemService.addMenuItem(menuItem));
        verify(menuItemRepository, never()).save(any(MenuItemEntity.class));
    }

    @Test
    void updateMenuItem_WithValidCategory_ShouldUpdateMenuItem() {
        // Arrange
        Long menuItemId = 1L;
        when(menuItemRepository.existsById(menuItemId)).thenReturn(true);
        when(categoryRepository.findById(validCategory.getCategoryId())).thenReturn(Optional.of(validCategory));
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);

        // Act
        menuItemService.updateMenuItem(menuItemId, menuItem);

        // Assert
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    void updateMenuItem_WithInvalidCategory_ShouldThrowException() {
        // Arrange
        Long menuItemId = 1L;
        when(menuItemRepository.existsById(menuItemId)).thenReturn(true);
        when(categoryRepository.findById(validCategory.getCategoryId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomNotFoundException.class, () -> menuItemService.updateMenuItem(menuItemId, menuItem));
        verify(menuItemRepository, never()).save(any(MenuItemEntity.class));
    }

    @Test
    void updateMenuItem_WithNullCategory_ShouldThrowException() {
        // Arrange
        Long menuItemId = 1L;
        menuItem.setCategory(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> menuItemService.updateMenuItem(menuItemId, menuItem));
        verify(menuItemRepository, never()).save(any(MenuItemEntity.class));
    }
}
