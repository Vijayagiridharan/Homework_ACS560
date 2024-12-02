-- Drop the tables if they exist
BEGIN;

use restaurant_db;

DROP TABLE IF EXISTS MENU_ITEM_ENTITY;
DROP TABLE IF EXISTS CATEGORY_ENTITY;

-- Create Categories Table
CREATE TABLE IF NOT EXISTS CATEGORY_ENTITY (
    CATEGORY_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CATEGORY_NAME VARCHAR(50) NOT NULL UNIQUE
);

-- Insert sample data into CATEGORY_ENTITY
INSERT INTO CATEGORY_ENTITY (CATEGORY_NAME) VALUES
('Main Course'),
('Dessert'),
('Beverage'),
('Appetizer'),
('Asian'),
('Italian'),
('Indian'),
('American');

-- Create MenuItems Table
CREATE TABLE IF NOT EXISTS MENU_ITEM_ENTITY (
    ITEM_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    ITEM_NAME VARCHAR(255) NOT NULL,
    CATEGORY_ID INT NOT NULL,
    PRICE DECIMAL(10, 2) NOT NULL,
    CALORIES INT NOT NULL,
    PREPARATION_TIME INT NOT NULL,
    CUISINE_TYPE VARCHAR(50),
    VEGETARIAN BOOLEAN,
    SPICY_LEVEL INT,
    AVAILABILITY BOOLEAN,
    CATEGORY_NAME VARCHAR(50),
    DESCRIPTION TEXT,
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY_ENTITY(CATEGORY_ID) ON DELETE CASCADE
);

-- Insert sample data into MENU_ITEM_ENTITY
INSERT INTO MENU_ITEM_ENTITY (ITEM_NAME, CATEGORY_ID, PRICE, CALORIES, PREPARATION_TIME, CUISINE_TYPE, VEGETARIAN, SPICY_LEVEL, AVAILABILITY, CATEGORY_NAME, DESCRIPTION) VALUES
('Grilled Chicken', 1, 12.99, 400, 30, 'American', false, 2, true, 'Main Course', 'Juicy grilled chicken with herbs.'),
('Caesar Salad', 1, 8.99, 200, 15, 'Italian', true, 1, true, 'Main Course', 'Fresh romaine lettuce with Caesar dressing.'),
('Chocolate Cake', 2, 5.99, 350, 20, 'Dessert', true, 0, true, 'Dessert', 'Rich and moist chocolate cake.'),
('Lemonade', 3, 2.99, 100, 5, 'Beverage', true, 0, true, 'Beverage', 'Refreshing lemonade.'),
('Spring Rolls', 4, 4.99, 150, 10, 'Asian', true, 1, true, 'Appetizer', 'Crispy spring rolls with dipping sauce.'),
('Spicy Tofu Stir-Fry', 1, 9.99, 300, 25, 'Asian', true, 3, true, 'Main Course', 'Stir-fried tofu with mixed vegetables and spices.'),
('Vanilla Ice Cream', 2, 3.49, 250, 10, 'Dessert', true, 0, true, 'Dessert', 'Creamy vanilla ice cream topped with chocolate sauce.'),
('Margarita Pizza', 1, 11.99, 450, 20, 'Italian', true, 1, true, 'Main Course', 'Classic pizza topped with fresh tomatoes and basil.'),
('Vegetable Curry', 1, 10.99, 300, 35, 'Indian', true, 4, true, 'Main Course', 'Mixed vegetables cooked in a spicy curry sauce.'),
('Chicken Wings', 1, 9.99, 500, 40, 'American', false, 3, true, 'Appetizer', 'Spicy chicken wings with blue cheese dressing.'),
('Chicken Tenders', 1, 9.99, 500, 40, 'American', false, 3, true, 'Appetizer', 'Spicy chicken tenders with blue cheese dressing.');


COMMIT;