package solahkay.binar.challenge.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParsingUtilTest {

    @Test
    void testIsIntegerSuccess_WhenReturnTrue() {
        assertTrue(ParsingUtil.isInteger("1"));
    }


    @Test
    void testIsIntegerFailed_WhenReturnFalse() {
        assertFalse(ParsingUtil.isInteger("not number"));
    }

}
