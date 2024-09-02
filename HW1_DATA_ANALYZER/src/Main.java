
public class Main {
    public static void main(String[] args) {
        String csvFilePath = "menu_items.csv"; // Update this path if necessary

        // Read the CSV file
        CSVReader csvReader = new CSVReader();
        DataAnalyzer dataAnalyzer = new DataAnalyzer(csvReader.readCSV(csvFilePath));

        // Perform analyses
        dataAnalyzer.printItemsPerCategory();
        dataAnalyzer.printAveragePricePerCategory();
        dataAnalyzer.printMostAndLeastExpensiveItems();
        dataAnalyzer.printAveragePreparationTime();
        dataAnalyzer.printAvailabilityCounts();
        dataAnalyzer.printHighestCalorieItem();
    }
}
