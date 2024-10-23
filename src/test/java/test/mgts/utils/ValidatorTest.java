package test.mgts.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    private static final String SHORT_PASS_NUMBER = "00F1C31";
    private static final String LONG_PASS_NUMBER = "00F1C3133";
    private static final String INCORRECT_PASS_NUMBER = "00f1C313";
    private static final String CORRECT_PASS_NUMBER = "00F1C313";


    @Test
    void validateIncorrectPassNumber() {
        assertThrows(ValidationFormatException.class, () -> Validator.validatePassNumber(INCORRECT_PASS_NUMBER));
    }

    @Test
    void validateShortPassNumber() {
        assertThrows(ValidationLengthNumberException.class, () -> Validator.validatePassNumber(SHORT_PASS_NUMBER));
    }

    @Test
    void validateLongPassNumber() {
        assertThrows(ValidationLengthNumberException.class, () -> Validator.validatePassNumber(LONG_PASS_NUMBER));
    }

    @Test
    void validateCorrectPassNumber() {
        assertDoesNotThrow(() -> Validator.validatePassNumber(CORRECT_PASS_NUMBER));
    }
}