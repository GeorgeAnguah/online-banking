package com.onlinebanking.shared.util.validation;

import com.onlinebanking.TestUtils;
import com.onlinebanking.enums.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * InputValidationUtilsTest class holds unit tests for methods in InputValidationUtils.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
class InputValidationUtilsTest {

    @Test
    void callingConstructorShouldThrowException() {
        TestUtils.assertExceptionCause(
                InputValidationUtils.class,
                AssertionError.class
        );
    }

    @Test
    void callingConstructorShouldThrowExceptionWithMessage() {
        TestUtils.assertExceptionCause(
                InputValidationUtils.class,
                AssertionError.class,
                ErrorMessage.NOT_INSTANTIABLE.getErrorMsg()
        );
    }

    @ParameterizedTest
    @MethodSource({"blankStrings"})
    void invalidInputThrowsException() {
        String input = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> InputValidationUtils.validateInputs(input));
    }

    @Test
    void validInputsDoesNotThrowException(TestInfo testInfo) {
        Assertions.assertDoesNotThrow(() -> InputValidationUtils.validateInputs(testInfo));
    }


    @Test
    void nullAndValidArrayInputThrowsException(TestInfo testInfo) {
        Object[] objects = {null, testInfo.getDisplayName()};
        String message = "An empty array should not be accepted as a valid input";
        Class<? extends InputValidationUtilsTest> clazz = getClass();

        var expectedType = IllegalArgumentException.class;
        Assertions.assertThrows(expectedType, () -> InputValidationUtils.validateInputs(message, clazz, objects));
    }

    @Test
    void validAndBlankArrayInputThrowsException(TestInfo testInfo) {
        Object[] objects = {testInfo.getDisplayName(), " "};
        String message = "An empty array should not be accepted as a valid input";

        var expectedType = IllegalArgumentException.class;
        Assertions.assertThrows(expectedType, () -> InputValidationUtils.validateInputs(message, objects));
    }


    @Test
    void emptyArrayInputThrowsException() {
        Object[] objects = {};
        Class<? extends InputValidationUtilsTest> clazz = getClass();

        var expectedType = IllegalArgumentException.class;
        Assertions.assertThrows(expectedType, () -> InputValidationUtils.validateInputs(clazz, objects));
    }

    @Test
    void validArrayInputDoesNotThrowException(TestInfo testInfo) {
        Object[] objects = {testInfo.getDisplayName()};
        Class<? extends InputValidationUtilsTest> clazz = getClass();

        Assertions.assertDoesNotThrow(() -> InputValidationUtils.validateInputs(clazz, objects));
    }

    @Test
    void validArrayWithEmptyArrayInputThrowsException() {
        Object[] objects = {new String[]{}};
        Class<? extends InputValidationUtilsTest> clazz = getClass();

        var expectedType = IllegalArgumentException.class;
        Assertions.assertThrows(expectedType, () -> InputValidationUtils.validateInputs(clazz, objects));
    }

    @Test
    void validArrayWithArrayInputsShouldBeValid(TestInfo testInfo) {
        Object[] objects = {testInfo.getDisplayName(), new String[]{testInfo.getDisplayName()}};
        Class<? extends InputValidationUtilsTest> clazz = getClass();

        Assertions.assertDoesNotThrow(() -> InputValidationUtils.validateInputs(clazz, objects));
    }

    @Test
    void validArrayWithPrimitiveArrayInputsShouldBeValid(TestInfo testInfo) {
        Object[] objects = {testInfo.getDisplayName(), new int[]{1}};
        Class<? extends InputValidationUtilsTest> clazz = getClass();

        Assertions.assertDoesNotThrow(() -> InputValidationUtils.validateInputs(clazz, objects));
    }

    private static Stream<String> blankStrings() {
        return Stream.of("", "   ", null);
    }
}