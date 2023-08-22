package solahkay.binar.challenge.util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    private InputUtil() {}

    public static String input(String message) {
        System.out.print(message + " ");
        return scanner.nextLine();
    }

}
