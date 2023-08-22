package solahkay.binar.challenge.generator;

public class IncrementIDGenerator {

    private static Long currentID = 1L;

    private IncrementIDGenerator() {}

    public static Long generate() {
        return currentID++;
    }

}
