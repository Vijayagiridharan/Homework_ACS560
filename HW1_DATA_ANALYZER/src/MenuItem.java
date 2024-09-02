
public class MenuItem {
    private int itemID;
    private String itemName;
    private int categoryID;
    private double price;
    private int calories;
    private int preparationTime;
    private String cuisineType;
    private boolean isVegetarian;
    private int spicyLevel;
    private boolean isAvailable;
    private String categoryName;
    private String description;

    public MenuItem(int itemID, String itemName, int categoryID, double price, int calories, int preparationTime,
                    String cuisineType, boolean isVegetarian, int spicyLevel, boolean isAvailable,
                    String categoryName, String description) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.categoryID = categoryID;
        this.price = price;
        this.calories = calories;
        this.preparationTime = preparationTime;
        this.cuisineType = cuisineType;
        this.isVegetarian = isVegetarian;
        this.spicyLevel = spicyLevel;
        this.isAvailable = isAvailable;
        this.categoryName = categoryName;
        this.description = description;
    }

    public int getItemID() { return itemID; }
    public String getItemName() { return itemName; }
    public int getCategoryID() { return categoryID; }
    public double getPrice() { return price; }
    public int getCalories() { return calories; }
    public int getPreparationTime() { return preparationTime; }
    public String getCuisineType() { return cuisineType; }
    public boolean isVegetarian() { return isVegetarian; }
    public int getSpicyLevel() { return spicyLevel; }
    public boolean isAvailable() { return isAvailable; }
    public String getCategoryName() { return categoryName; }
    public String getDescription() { return description; }
}
