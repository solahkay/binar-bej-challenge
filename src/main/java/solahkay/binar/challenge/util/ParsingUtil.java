package solahkay.binar.challenge.util;

public class ParsingUtil {

    private ParsingUtil() {
    }

    public static boolean isNumber(String input) {
        return input.matches("^-?\\d+$");
    }

}
