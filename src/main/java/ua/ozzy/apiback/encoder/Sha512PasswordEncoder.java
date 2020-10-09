package ua.ozzy.apiback.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import ua.ozzy.apiback.exception.ApiBackException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512PasswordEncoder implements PasswordEncoder {

    private static final String HASHING_ALG = "SHA-512";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String rawPasswordHash = encode(rawPassword);
        return encodedPassword.equals(rawPasswordHash);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance(HASHING_ALG);
            byte[] rawBytes = rawPassword.toString().getBytes(DEFAULT_CHARSET);
            byte[] hashedBytes = msgDigest.digest(rawBytes);
            return asHexString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new ApiBackException(e);
        }
    }

    private String asHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int bAsInt = 0xff & b; // zero all leftmost bits when cast to int
            String hexStr = Integer.toHexString(bAsInt);
            sb.append(hexStr);
        }
        return sb.toString();
    }

}
