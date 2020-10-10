package ua.ozzy.apiback.exception;

import java.util.Map;

public class FieldErrorsException extends RuntimeException {

    private final Map<String, String> fieldErrors;

    public FieldErrorsException(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

}
