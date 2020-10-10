package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class UkrainePhoneNumberCodeService implements PhoneNumberCountryCodeService {

    private static final Pattern UKR_PHONE_NUMBER_CODE_PATTERN = Pattern.compile("^\\+?(380)");
    private static final short PHONE_NUMBER_WITH_UKR_CODE_LENGTH = 12;

    @Override
    public boolean startsWithCountryCode(String phoneNum) {
        validateNotNull(phoneNum, "Phone number is null");
        Matcher m = UKR_PHONE_NUMBER_CODE_PATTERN.matcher(phoneNum);
        return m.find() && phoneNum.length() == PHONE_NUMBER_WITH_UKR_CODE_LENGTH;
    }

    @Override
    public String removeCountryCode(String phoneNum) {
        validateNotNull(phoneNum, "Phone number is null");
        return phoneNum.replace(UKR_PHONE_NUMBER_CODE_PATTERN.pattern(), "");
    }

}
