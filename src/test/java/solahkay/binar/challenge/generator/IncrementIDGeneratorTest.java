package solahkay.binar.challenge.generator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncrementIDGeneratorTest {

    @ParameterizedTest(name = "{displayName}{0}Times")
    @ValueSource(longs = {1L, 2L, 3L, 4L})
    void testGenerateSuccess_WhenGenerate(long value) {
        Long expected = value;
        Long result = IncrementIDGenerator.generate();

        assertEquals(expected, result);
    }
}
