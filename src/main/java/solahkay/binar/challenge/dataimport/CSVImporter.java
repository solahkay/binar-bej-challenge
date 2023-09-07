package solahkay.binar.challenge.dataimport;

import solahkay.binar.challenge.App;
import solahkay.binar.challenge.service.MenuService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVImporter {

    private CSVImporter() {
    }

    public static void csvImport(MenuService menuService, String path) {
        String csvDelimiter = ";";

        try (InputStream inputStream = App.class.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IOException("Can't access the menu file: typo or file not found");
            }

            try (InputStreamReader streamReader = new InputStreamReader(inputStream);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // split 2 data (name and price) into array
                    String[] data = line.split(csvDelimiter);

                    menuService.addMenu(data[0], Integer.parseInt(data[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // shutdown application because of fatal error
            System.exit(1);
        }
    }

}
