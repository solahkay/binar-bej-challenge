package solahkay.binar.challenge.generator;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncrementIDGeneratorTest {

    @RepeatedTest(value = 10, name = "{displayName}{currentRepetition}Times")
    void testGenerateSuccess_WhenGenerate() {
        Long result = IncrementIDGenerator.generate();

        assertNotNull(result);
        assertTrue(result > 0);
    }

}
