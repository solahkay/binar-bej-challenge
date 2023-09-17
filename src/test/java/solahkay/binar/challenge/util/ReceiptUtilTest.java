package solahkay.binar.challenge.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ReceiptUtilTest {

    @Test
    void testWriteReceiptToTxtFileSuccess_WhenSuccessCreateFile() {
        String receipt = "test";
        String path = "receipt.txt";

        assertDoesNotThrow(() -> {
            ReceiptUtil.writeReceiptToTxtFile(receipt, path);
        });
    }

    @Test
    void testWriteReceiptToTxtFileFailed_WhenFailed() {
        String receipt = "test";
        String path = "/a/b/c/d/e/f/h.txt";

        assertDoesNotThrow(() -> {
            ReceiptUtil.writeReceiptToTxtFile(receipt, path);
        });
    }

}
