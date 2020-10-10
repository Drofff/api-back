package ua.ozzy.apiback.util;

import ua.ozzy.apiback.exception.FieldErrorsException;
import ua.ozzy.apiback.exception.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class ValidationUtil {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    private ValidationUtil() {}

    public static <T> void validate(T obj, String msgIfNull) {
        validateNotNull(obj, msgIfNull);
        Set<ConstraintViolation<T>> violations = VALIDATOR.validate(obj);
        if (!violations.isEmpty()) {
            Map<String, String> fieldErrors = asFieldErrorsMap(violations);
            throw new FieldErrorsException(fieldErrors);
        }
    }

    public static void validateNotNull(Object obj, String errMsg) {
        if (obj == null) {
            throw new ValidationException(errMsg);
        }
    }

    private static <T> Map<String, String> asFieldErrorsMap(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .collect(toMap(v -> v.getPropertyPath().toString(), ConstraintViolation::getMessage));
    }

}
