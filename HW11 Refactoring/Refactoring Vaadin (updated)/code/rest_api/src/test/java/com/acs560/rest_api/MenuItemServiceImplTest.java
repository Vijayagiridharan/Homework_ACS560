package com.acs560.rest_api;

import com.acs560.rest_api.entities.CategoryEntity;
import com.acs560.rest_api.entities.MenuItemEntity;
import com.acs560.rest_api.repositories.MenuItemRepository;
import com.acs560.rest_api.services.impl.MenuItemServiceImpl;
import com.acs560.rest_api.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuItemServiceImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    private CategoryEntity category;
    private MenuItemEntity menuItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a category entity
        category = new CategoryEntity("Beverages");
        category.setCategoryId(1L); // Set the categoryId manually

        // Create a menu item entity
        menuItem = new MenuItemEntity("Coca-Cola", new BigDecimal("2.50"));
        menuItem.setCategory(category);
        menuItem.setItemId(1L); // Set the itemId manually
    }

    @Test
    void testAddMenuItem() {
        // Mock the behavior of the repository methods
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(menuItemRepository.save(any(MenuItemEntity.class))).thenReturn(menuItem);

        // Call the method to test
        MenuItemEntity savedMenuItem = menuItemService.addMenuItem(menuItem);

        // Verify the result
        assertNotNull(savedMenuItem);
        assertEquals(menuItem.getItemName(), savedMenuItem.getItemName());
        assertEquals(menuItem.getPrice(), savedMenuItem.getPrice());
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    void testUpdateMenuItem() {
        // Mock the behavior of the repository methods
        when(menuItemRepository.existsById(anyLong())).thenReturn(true);
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(menuItemRepository.save(any(MenuItemEntity.class))).thenReturn(menuItem);

        // Call the method to test
        menuItemService.updateMenuItem(menuItem.getItemId(), menuItem);

        // Verify the result
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    void testDeleteMenuItem() {
        // Mock the behavior of the repository methods
        when(menuItemRepository.existsById(anyLong())).thenReturn(true);

        // Call the method to test
        menuItemService.deleteMenuItem(menuItem.getItemId());

        // Verify the result
        verify(menuItemRepository, times(1)).deleteById(menuItem.getItemId());
    }

    @Test
    void testGetMenuItems() {
        // Mock the behavior of the repository methods
        List<MenuItemEntity> menuItems = new ArrayList<>();
        menuItems.add(menuItem);
        when(menuItemRepository.findAll()).thenReturn(menuItems);

        // Call the method to test
        List<MenuItemEntity> retrievedMenuItems = menuItemService.getMenuItems();

        // Verify the result
        assertNotNull(retrievedMenuItems);
        assertEquals(1, retrievedMenuItems.size());
        assertEquals(menuItem.getItemName(), retrievedMenuItems.get(0).getItemName());
    }

    @Test
    void testGetMenuItemsByCategory() {
        // Mock the behavior of the repository methods
        List<MenuItemEntity> menuItems = new ArrayList<>();
        menuItems.add(menuItem);
        when(menuItemRepository.findByCategory(category)).thenReturn(menuItems);

        // Call the method to test
        List<MenuItemEntity> retrievedMenuItems = menuItemService.getMenuItemsByCategory(category);

        // Verify the result
        assertNotNull(retrievedMenuItems);
        assertEquals(1, retrievedMenuItems.size());
        assertEquals(menuItem.getItemName(), retrievedMenuItems.get(0).getItemName());
    }
}
