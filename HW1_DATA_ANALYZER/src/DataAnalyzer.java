

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAnalyzer {
    private List<MenuItem> menuItems;

    public DataAnalyzer(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void printItemsPerCategory() {
        Map<String, Long> itemsPerCategory = new HashMap<>();
        for (MenuItem item : menuItems) {
            itemsPerCategory.put(item.getCategoryName(), itemsPerCategory.getOrDefault(item.getCategoryName(), 0L) + 1);
        }
        System.out.println("Number of items per category: " + itemsPerCategory);
    }

    public void printAveragePricePerCategory() {
        Map<String, Double> totalPricesPerCategory = new HashMap<>();
        Map<String, Long> itemCountPerCategory = new HashMap<>();

        for (MenuItem item : menuItems) {
            totalPricesPerCategory.put(item.getCategoryName(), totalPricesPerCategory.getOrDefault(item.getCategoryName(), 0.0) + item.getPrice());
            itemCountPerCategory.put(item.getCategoryName(), itemCountPerCategory.getOrDefault(item.getCategoryName(), 0L) + 1);
        }

        Map<String, Double> avgPricePerCategory = new HashMap<>();
        for (String category : totalPricesPerCategory.keySet()) {
            avgPricePerCategory.put(category, totalPricesPerCategory.get(category) / itemCountPerCategory.get(category));
        }
        System.out.println("Average price per category: " + avgPricePerCategory);
    }

    public void printMostAndLeastExpensiveItems() {
        MenuItem mostExpensive = Collections.max(menuItems, Comparator.comparingDouble(MenuItem::getPrice));
        MenuItem leastExpensive = Collections.min(menuItems, Comparator.comparingDouble(MenuItem::getPrice));
        System.out.println("Most expensive item: " + mostExpensive.getItemName() + " ($" + mostExpensive.getPrice() + ")");
        System.out.println("Least expensive item: " + leastExpensive.getItemName() + " ($" + leastExpensive.getPrice() + ")");
    }

    public void printAveragePreparationTime() {
        double totalVegPrepTime = 0.0;
        double totalNonVegPrepTime = 0.0;
        int vegCount = 0;
        int nonVegCount = 0;

        for (MenuItem item : menuItems) {
            if (item.isVegetarian()) {
                totalVegPrepTime += item.getPreparationTime();
                vegCount++;
            } else {
                totalNonVegPrepTime += item.getPreparationTime();
                nonVegCount++;
            }
        }

        System.out.println("Average preparation time for vegetarian items: " + (vegCount > 0 ? totalVegPrepTime / vegCount : 0) + " minutes");
        System.out.println("Average preparation time for non-vegetarian items: " + (nonVegCount > 0 ? totalNonVegPrepTime / nonVegCount : 0) + " minutes");
    }

    public void printAvailabilityCounts() {
        long availableCount = menuItems.stream().filter(MenuItem::isAvailable).count();
        long unavailableCount = menuItems.size() - availableCount;
        System.out.println("Number of available items: " + availableCount);
        System.out.println("Number of unavailable items: " + unavailableCount);
    }

    public void printHighestCalorieItem() {
        MenuItem highestCalorieItem = Collections.max(menuItems, Comparator.comparingInt(MenuItem::getCalories));
        System.out.println("Item with the highest calories: " + highestCalorieItem.getItemName() + " (" + highestCalorieItem.getCalories() + " calories)");
    }
}
