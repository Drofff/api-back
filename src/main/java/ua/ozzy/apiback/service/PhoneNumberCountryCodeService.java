package ua.ozzy.apiback.service;

public interface PhoneNumberCountryCodeService {

    boolean startsWithCountryCode(String phoneNumber);

    String removeCountryCode(String phoneNumber);

}
