package ua.ozzy.apiback.bitrix.format;

import org.springframework.stereotype.Service;

/**
 * Formats camel case keys as snake case strings with upper-case
 * letters. This format is expected by the Bitrix API for field
 * parameter keys.
 * Example: firstName -> FIRST_NAME
 */
@Service
public class SnakeParamKeysFormatter extends AbstractParamKeysFormatter {

    private static final char WORDS_DELIMITER = '_';

    @Override
    protected String formatKey(String key) {
        StringBuilder formattedKey = new StringBuilder();
        for (char ch : key.toCharArray()) {
            if (isUppercaseLetter(ch)) {
                formattedKey.append(WORDS_DELIMITER);
                formattedKey.append(ch);
            } else {
                char uppercaseCh = Character.toUpperCase(ch);
                formattedKey.append(uppercaseCh);
            }
        }
        return formattedKey.toString();
    }

    private boolean isUppercaseLetter(char ch) {
        return Character.isLetter(ch) && Character.isUpperCase(ch);
    }

}
