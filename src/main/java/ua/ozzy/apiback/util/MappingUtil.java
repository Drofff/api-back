package ua.ozzy.apiback.util;

import java.lang.reflect.Field;

import static ua.ozzy.apiback.util.ReflectionUtil.eachField;

public class MappingUtil {

    private MappingUtil() {}

    public static <S, D> D copyMatchingFields(S src, D dst) {
        eachField(src)
                .filter(srcField -> hasMatchingField(dst, srcField))
                .forEach(srcField -> {
                    Object val = ReflectionUtil.getValueOfField(src, srcField);
                    ReflectionUtil.setValueOfFieldWithName(dst, val, srcField.getName());
                });
        return dst;
    }

    private static boolean hasMatchingField(Object obj, Field field) {
        return eachField(obj).anyMatch(f -> fieldsMatch(f, field));
    }

    private static boolean fieldsMatch(Field f0, Field f1) {
        return f0.getName().equals(f1.getName()) && f0.getType().equals(f1.getType());
    }

}
