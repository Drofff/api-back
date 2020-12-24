package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class UkrainePhoneNumberCodeService implements PhoneNumberCountryCodeService {

    private static final Pattern UKR_CODE_PATTERN = Pattern.compile("^\\+?38");

    @Override
    public boolean startsWithCountryCode(String phoneNum) {
        validateNotNull(phoneNum, "Phone number is null");
        return UKR_CODE_PATTERN.matcher(phoneNum).find();
    }

    @Override
    public String removeCountryCode(String phoneNum) {
        validateNotNull(phoneNum, "Phone number is null");
        return UKR_CODE_PATTERN.matcher(phoneNum).replaceAll("");
    }

}
