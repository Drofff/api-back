package ua.ozzy.apiback.util;

import ua.ozzy.apiback.exception.ApiBackException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

    private static final String HASHING_ALG = "SHA-512";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    private CryptoUtil() {}

    public static String hashStr(String str) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance(HASHING_ALG);
            byte[] hashedBytes = msgDigest.digest(str.getBytes(DEFAULT_CHARSET));
            return asHexString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new ApiBackException(e);
        }
    }

    private static String asHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int bAsInt = 0xff & b; // zero all leftmost bits when cast to int
            String hexStr = Integer.toHexString(bAsInt);
            sb.append(hexStr);
        }
        return sb.toString();
    }

}
