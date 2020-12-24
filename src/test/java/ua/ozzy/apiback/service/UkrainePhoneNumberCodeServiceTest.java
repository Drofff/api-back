package ua.ozzy.apiback.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UkrainePhoneNumberCodeServiceTest {

    private final UkrainePhoneNumberCodeService service = new UkrainePhoneNumberCodeService();

    @Test
    void startsWithCountryCodeTest() {
        String testPhoneNum = "+380662888999";
        boolean startsWithCC = service.startsWithCountryCode(testPhoneNum);
        assertTrue(startsWithCC);
    }

    @Test
    void startsWithCountryCodeFalseTest() {
        String testPhoneNum = "0662888999";
        boolean startsWithCC = service.startsWithCountryCode(testPhoneNum);
        assertFalse(startsWithCC);
    }

    @Test
    void removeCountryCodeTest() {
        String testPhoneNum = "+380662888999";
        String expectedResult = "0662888999";
        String actualResult = service.removeCountryCode(testPhoneNum);
        assertEquals(expectedResult, actualResult);
    }

}
