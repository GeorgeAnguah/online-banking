package com.onlinebanking.shared.util;

import com.onlinebanking.enums.ErrorMessage;
import lombok.Getter;
import org.springframework.context.ApplicationContext;

/**
 * User utility class that holds methods used to retrieve spring beans from a non managed class.
 * This is a typical service locator pattern in software engineering.
 * For more details: https://en.wikipedia.org/wiki/Service_locator_pattern
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
public final class ApplicationContextUtils {

    @Getter
    private static ApplicationContext ctx;

    private ApplicationContextUtils() {
        throw new AssertionError(ErrorMessage.NOT_INSTANTIABLE.getErrorMsg());
    }

    /**
     * Sets the application context right after the application context is ready via SpringContext.
     *
     * @param applicationContext the application context
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    /**
     * Retrieves a registered bean from the application context.
     *
     * @param beanClass the bean to retrieve
     * @param <T>       the bean type
     *
     * @return the bean
     */
    public static <T> T getBean(Class<T> beanClass) {
        return ctx.getBean(beanClass);
    }
}
