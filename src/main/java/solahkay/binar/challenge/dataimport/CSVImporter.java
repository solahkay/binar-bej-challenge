package solahkay.binar.challenge.dataimport;

import solahkay.binar.challenge.App;
import solahkay.binar.challenge.service.MenuService;

import java.io.*;

public class CSVImporter {

    private CSVImporter() {}

    public static void csvImport(MenuService menuService, String path) throws IOException {
        String csvDelimiter = ";";

        try (InputStream inputStream = App.class.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IOException();
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream)
            )) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // split 2 data (name and price) into array
                    String[] data = line.split(csvDelimiter);

                    menuService.addMenu(data[0], Integer.parseInt(data[1]));
                }
            }
        }
    }

}