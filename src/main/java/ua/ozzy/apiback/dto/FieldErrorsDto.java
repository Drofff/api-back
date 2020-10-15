package ua.ozzy.apiback.dto;

import java.util.Map;

public class FieldErrorsDto {

    private final Map<String, String> fieldErrors;

    public FieldErrorsDto(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

}
