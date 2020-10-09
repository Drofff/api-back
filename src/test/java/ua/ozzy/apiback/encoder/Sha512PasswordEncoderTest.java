package ua.ozzy.apiback.encoder;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class Sha512PasswordEncoderTest {

    @Test
    public void encodeAndMatchPasswordTest() {
        String testPassword = "pass";
        PasswordEncoder pe = new Sha512PasswordEncoder();
        String encodedPassword = pe.encode(testPassword);
        assertNotEquals(testPassword, encodedPassword);
        boolean passwordMatchesHash = pe.matches(testPassword, encodedPassword);
        assertTrue(passwordMatchesHash);
    }

    @Test
    public void hashIsConsistentTest() {
        String testPassword = "pass";
        PasswordEncoder pe = new Sha512PasswordEncoder();
        long distinctHashesCount = IntStream.range(0, 4)
                .mapToObj(i -> pe.encode(testPassword))
                .distinct()
                .count();
        assertEquals(1, distinctHashesCount);
    }

}
