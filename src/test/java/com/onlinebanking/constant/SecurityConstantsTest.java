package com.onlinebanking.constant;

import com.onlinebanking.enums.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test SecurityConstants.
 *
 * @author George on 7/8/2021
 * @version 1.0
 * @since 1.0
 */
class SecurityConstantsTest {

    @Test
    void constructorCallShouldThrowException() {
        // use reflection to get private SecurityConstants constructor
        // Call constructor
        // Assert throws the exception wrap in InvocationTargetException.
        // Assert InvocationTargetException and assert it's contain equals AssertError thrown by private constructor

        Class<?> reflectSecurityConstantsClass = SecurityConstants.class;
        Constructor<?>[] declaredConstructors = reflectSecurityConstantsClass.getDeclaredConstructors();
        Constructor<?> privateConstructor = declaredConstructors[0];

        privateConstructor.setAccessible(true);

        Exception exception = assertThrows(InvocationTargetException.class, () -> privateConstructor.newInstance());
        assertEquals(AssertionError.class, exception.getCause().getClass());

    }

    @Test
    void testPublicMatcherNotEmpty(){
        assertNotNull(SecurityConstants.getPublicMatchers());
    }
}
