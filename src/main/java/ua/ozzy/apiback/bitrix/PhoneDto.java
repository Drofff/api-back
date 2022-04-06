package ua.ozzy.apiback.bitrix;

public class PhoneDto {

    private static final String MOBILE_TYPE = "MOBILE";

    private final String value;
    private final String valueType;

    private PhoneDto(String value, String valueType) {
        this.value = value;
        this.valueType = valueType;
    }

    public static PhoneDto newMobilePhone(String phoneNum) {
        return new PhoneDto(phoneNum, MOBILE_TYPE);
    }

    public String getValue() {
        return value;
    }

    public String getValueType() {
        return valueType;
    }

}
