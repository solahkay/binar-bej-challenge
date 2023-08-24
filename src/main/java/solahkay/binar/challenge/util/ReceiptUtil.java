package solahkay.binar.challenge.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReceiptUtil {

    private ReceiptUtil() {
    }

    public static String createReceipt(String orderList) {
        String wrapper = "==========================";
        String delimiter = System.getProperty("line.separator");

        return String.join(
                delimiter,
                wrapper,
                "BinarFud",
                wrapper,
                "",
                "Terima kasih sudah memesan",
                "di BinarFud",
                "",
                "Di bawah ini adalah pesanan anda",
                "",
                orderList,
                "",
                "Pembayaran : BinarCash",
                "",
                wrapper,
                "Simpan struk ini sebagai",
                "bukti pembayaran",
                wrapper
        );
    }

    public static void writeReceiptToTxtFile(String receipt) {
        Path path = Paths.get("receipt.txt");
        byte[] receiptBytes = receipt.getBytes();

        try {
            Files.write(path, receiptBytes);
            System.out.println("\nStruk berhasil dicetak di receipt.txt");
        } catch (IOException exception) {
            System.out.println("\nStruk gagal dicetak");
        }
    }

}
