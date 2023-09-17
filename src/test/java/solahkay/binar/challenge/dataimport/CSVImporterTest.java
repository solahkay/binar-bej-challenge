package solahkay.binar.challenge.dataimport;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import solahkay.binar.challenge.service.MenuService;

import static org.junit.jupiter.api.Assertions.*;

@Extensions({
        @ExtendWith(MockitoExtension.class)
})
class CSVImporterTest {

    @Mock
    private MenuService menuService;

    @Test
    void testCsvImportSuccess_WhenFileExist() {
        assertDoesNotThrow(() -> {
            CSVImporter.csvImport(menuService, "/menu.csv");
        });

        Mockito.verify(menuService, Mockito.times(1))
                .addMenu("Nasi Goreng", 15000);
    }

    @Test
    void testCsvImportFailed_WhenFileNotExist() throws Exception {
        int expected = 1;
        int statusCode = SystemLambda.catchSystemExit(() -> {
            CSVImporter.csvImport(menuService, "/not-exist.csv");
        });

        assertEquals(expected, statusCode);
    }

    @Test
    void testCsvImportSuccess_WhenMenuIsDuplicateAndRemoveIt() {
        assertDoesNotThrow(() -> {
            CSVImporter.csvImport(menuService, "/menu-duplicate.csv");
        });

        Mockito.verify(menuService, Mockito.times(1))
                .addMenu("Nasi + Ayam", 18000);
    }

}
