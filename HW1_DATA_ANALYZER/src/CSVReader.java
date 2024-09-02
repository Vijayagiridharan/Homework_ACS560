
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public List<MenuItem> readCSV(String filePath) {
        List<MenuItem> menuItems = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                MenuItem item = new MenuItem(
                    Integer.parseInt(data[0]),
                    data[1],
                    Integer.parseInt(data[2]),
                    Double.parseDouble(data[3]),
                    Integer.parseInt(data[4]),
                    Integer.parseInt(data[5]),
                    data[6],
                    Boolean.parseBoolean(data[7]),
                    Integer.parseInt(data[8]),
                    Boolean.parseBoolean(data[9]),
                    data[10],
                    data[11]
                );
                menuItems.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuItems;
    }
}

