package com.onlinebanking.shared.util.validation;

import com.onlinebanking.enums.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Validation utility class that holds methods used across application for input validations.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public final class InputValidationUtils {

    private InputValidationUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }

    /**
     * A helper method which takes in multiple arguments and validate each
     * instance not being null.
     *
     * @param message custom message to be displayed
     * @param inputs  instances to be validated
     *
     * @throws IllegalArgumentException if any of the inputs is {@literal null}.
     */
    public static void validateInputs(String message, final Object... inputs) {
        validateInputs(message, null, inputs);
    }

    /**
     * A helper method which takes in multiple arguments and validate each
     * instance not being null.
     *
     * @param inputs instances to be validated
     *
     * @throws IllegalArgumentException if any of the inputs is {@literal null}.
     */
    public static void validateInputs(final Object... inputs) {
        validateInputs(null, null, inputs);
    }

    /**
     * A helper method which takes in multiple arguments and validate each
     * instance not being null.
     *
     * @param inputs instances to be validated
     * @param clazz  the class within which the exception is thrown
     *
     * @throws IllegalArgumentException if any of the inputs is {@literal null}.
     */
    public static void validateInputs(Class<?> clazz, final Object... inputs) {
        validateInputs(null, clazz, inputs);
    }

    /**
     * A helper method which takes in multiple arguments and validate each
     * instance not being null.
     *
     * @param message custom message to be displayed
     * @param inputs  instances to be validated
     * @param clazz   the class within which the exception is thrown
     *
     * @throws IllegalArgumentException if any of the inputs is {@literal null}.
     */
    public static void validateInputs(String message, Class<?> clazz, final Object... inputs) {
        var validationFailed = ArrayUtils.isEmpty(inputs);

        for (Object input : inputs) {
            if (Objects.isNull(input) || isObjectAnEmptyString(input) || isObjectAnEmptyArray(input)) {
                validationFailed = true;
            }
        }

        logAndThrowException(message, clazz, validationFailed);
    }

    /**
     * Checks if the object is a string then validates that it is not blank.
     *
     * @param object the object to validate
     *
     * @return if object is valid
     */
    private static boolean isObjectAnEmptyString(Object object) {
        return object.getClass() == String.class && StringUtils.isBlank((String) object);
    }

    /**
     * Checks if the object is an array then validates that it is not empty.
     *
     * @param object the object to validate
     *
     * @return if object is valid
     */
    private static boolean isObjectAnEmptyArray(Object object) {
        return object.getClass().isArray() && convertToObjectArray(object).length == 0;
    }

    /**
     * converts an object to an array if the object is an array.
     *
     * @param array the object
     * @return the array
     */
    private static Object[] convertToObjectArray(Object array) {
        Class<?> ofArray = array.getClass().getComponentType();
        if (ofArray.isPrimitive()) {
            List<Object> ar = new ArrayList<>();
            int length = Array.getLength(array);
            for (int i = 0; i < length; i++) {
                ar.add(Array.get(array, i));
            }
            return ar.toArray();
        } else {
            return (Object[]) array;
        }
    }

    /**
     * Logs the message if provided and includes class in exception for details if given.
     *
     * @param message          the message
     * @param clazz            the class
     * @param validationFailed if validation failed
     */
    private static void logAndThrowException(String message, Class<?> clazz, boolean validationFailed) {
        if (validationFailed) {
            if (Objects.nonNull(message)) {
                LOG.debug(message);
            }
            var errorMsg = ErrorMessage.NULL_ELEMENTS_NOT_ALLOWED.getErrorMsg();
            if (Objects.nonNull(clazz)) {
                var derivedDebugMessage = String.join(" in: ", errorMsg, clazz.getName());
                LOG.debug(derivedDebugMessage);
                throw new IllegalArgumentException(derivedDebugMessage);
            }
            throw new IllegalArgumentException(errorMsg);
        }
    }
}
