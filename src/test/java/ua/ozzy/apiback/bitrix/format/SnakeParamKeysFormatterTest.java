package ua.ozzy.apiback.bitrix.format;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SnakeParamKeysFormatterTest {

    private final SnakeParamKeysFormatter formatter = new SnakeParamKeysFormatter();

    @Test
    void formatKeyTest() {
        String camelCaseStr = "firstNameOfMyDog";
        String expectedOutput = "FIRST_NAME_OF_MY_DOG";
        String actualOutput = formatter.formatKey(camelCaseStr);
        assertEquals(expectedOutput, actualOutput);
    }

}
