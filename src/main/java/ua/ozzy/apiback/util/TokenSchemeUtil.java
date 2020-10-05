package ua.ozzy.apiback.util;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

public class TokenSchemeUtil {

    private static final String TOKEN_SCHEME_DELIMITER = " ";

    private TokenSchemeUtil() {}

    public static String getSchemeOfToken(String token) {
        tokenIsNotNull(token);
        return token.split(TOKEN_SCHEME_DELIMITER)[0];
    }

    public static String removeSchemeFromToken(String token) {
        tokenIsNotNull(token);
        return token.split(TOKEN_SCHEME_DELIMITER)[1];
    }

    private static void tokenIsNotNull(String token) {
        validateNotNull(token, "Token should not be null");
    }

}
