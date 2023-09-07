package solahkay.binar.challenge.util;

public class ParsingUtil {

    private ParsingUtil() {
    }

    public static boolean isLong(String input) {
        try {
            Long.parseLong(input);
            return isNumber(input);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return isNumber(input);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isNumber(String input) {
        return input.matches("^-?\\d+$");
    }

}
