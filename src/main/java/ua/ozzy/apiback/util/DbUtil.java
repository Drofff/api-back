package ua.ozzy.apiback.util;

import java.util.UUID;

public class DbUtil {

    private DbUtil() {}

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}
