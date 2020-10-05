package ua.ozzy.apiback.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CryptoUtilTest {

    @Test
    public void hashStrTest() {
        String testStr = "Hello, world!";
        String hashedStr = CryptoUtil.hashStr(testStr);
        assertNotEquals(testStr, hashedStr);
        String hashedOneAgainStr = CryptoUtil.hashStr(testStr);
        assertEquals(hashedStr, hashedOneAgainStr);
    }

}
