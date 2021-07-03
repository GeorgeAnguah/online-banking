package com.onlinebanking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The test class for the main application class.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
class OnlineBankingApplicationTests {

    @Mock
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test the main method with mocked application context.
     */
    @Test
    /* default */ void contextLoads() {
        var mockStatic = Mockito.mockStatic(SpringApplication.class);
        OnlineBankingApplication.main(new String[]{});
        mockStatic.when(() -> SpringApplication.run(OnlineBankingApplication.class)).thenReturn(applicationContext);
        mockStatic.verify(() -> SpringApplication.run(OnlineBankingApplication.class));
    }

}
