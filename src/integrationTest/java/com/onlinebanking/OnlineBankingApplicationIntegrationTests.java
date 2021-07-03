package com.onlinebanking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test class for the main application class.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
class OnlineBankingApplicationIntegrationTests {

    /**
     * Starts up the full application context to ensure all moving parts communicate successfully.
     */
    @Test
    /* default */ void contextLoads() {
        Assertions.assertDoesNotThrow(() -> OnlineBankingApplication.main(new String[]{}));
    }

}
