package ua.ozzy.apiback.util;

import ua.ozzy.apiback.exception.ApiBackException;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class ReflectionUtil {

    private ReflectionUtil() {}

    public static Object getValueOfField(Object src, Field field) {
        try {
            field.setAccessible(true);
            return field.get(src);
        } catch (IllegalAccessException e) {
            throw new ApiBackException(e.getMessage());
        }
    }

    public static void setValueOfFieldWithName(Object dst, Object value, String fieldName) {
        try {
            Field field = getFieldOfObjectByName(dst, fieldName);
            field.setAccessible(true);
            field.set(dst, value);
        } catch (IllegalAccessException e) {
            throw new ApiBackException(e.getMessage());
        }
    }

    private static Field getFieldOfObjectByName(Object obj, String fieldName) {
        return eachField(obj)
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElseThrow(() ->
                        new ApiBackException("Class " + obj.getClass().getName() + " has no field " + fieldName));
    }

    public static Stream<Field> eachField(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        return stream(fields);
    }

}
