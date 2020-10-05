package ua.ozzy.apiback.util;

import ua.ozzy.apiback.exception.ValidationException;

public class ValidationUtil {

    private ValidationUtil() {}

    public static void validateNotNull(Object obj, String errMsg) {
        if (obj == null) {
            throw new ValidationException(errMsg);
        }
    }

}
