package solahkay.binar.challenge.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputUtilTest {

    @Test
    void testInputSuccess_WhenReturnString() {
        String input = "test";
        InputStream stream = new ByteArrayInputStream(input.getBytes());

        System.setIn(stream);
        String expected = InputUtil.input("");
        System.setIn(System.in);

        assertEquals(expected, input);
    }

}
