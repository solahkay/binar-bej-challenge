package solahkay.binar.challenge.dataimport;

import solahkay.binar.challenge.service.MenuService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVImporter {

    private CSVImporter() {}

    public static void csvImport(MenuService menuService, String path) throws IOException {
        String csvDelimiter = ";";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // split 2 data (name and price) into array
                String[] data = line.split(csvDelimiter);

                menuService.addMenu(data[0], Integer.parseInt(data[1]));
            }
        }
    }

}
