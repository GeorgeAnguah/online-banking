package com.onlinebanking.shared.util.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * InputValidationUtilsTest class holds unit tests for methods in InputValidationUtils.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
class InputValidationUtilsTest {

    @Test
    void invalidInputAsEmptyThrowsException() {
        String input = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidationUtils.validateInputs(input));
    }

    @Test
    void invalidInputAsBlankThrowsException() {
        String input = " ";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidationUtils.validateInputs(input));
    }

    @Test
    void validInputsDoesNotThrowException(TestInfo testInfo) {
        Assertions.assertDoesNotThrow(() -> InputValidationUtils.validateInputs(testInfo));
    }

    @Test
    void emptyArrayInputThrowsException() {
        Object[] objects = {};
        String message = "An empty array should not be accepted as a valid input";
        Class<? extends InputValidationUtilsTest> clazz = getClass();

        var expectedType = IllegalArgumentException.class;
        Assertions.assertThrows(expectedType, () -> InputValidationUtils.validateInputs(message, clazz, objects));
    }

    @Test
    void validArrayInputDoesNotThrowException(TestInfo testInfo) {
        Object[] objects = {testInfo.getDisplayName()};
        Class<? extends InputValidationUtilsTest> clazz = getClass();

        Assertions.assertDoesNotThrow(() -> InputValidationUtils.validateInputs(clazz, objects));
    }
}