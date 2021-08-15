package com.onlinebanking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The test class for the main application class.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
class OnlineBankingApplicationIntegrationTests {

    /**
     * Test the main method with mocked application context.
     */
    @Test
    void testClassConstructor() {
        Assertions.assertDoesNotThrow(OnlineBankingApplication::new);
    }

}
