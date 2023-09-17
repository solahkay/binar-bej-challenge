package solahkay.binar.challenge.util;

public class PrintUtil {

    private PrintUtil() {
    }

    public static String addDotNumber(Long num) {
        String numString = Long.toString(num);
        int length = numString.length();

        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < length; i++) {
            formatted.append(numString.charAt(i));
            if ((length - i - 1) % 3 == 0 && i != length - 1) {
                formatted.append(".");
            }
        }
        return formatted.toString();
    }

}
