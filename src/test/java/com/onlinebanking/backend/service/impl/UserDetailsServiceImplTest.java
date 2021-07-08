package com.onlinebanking.backend.service.impl;

import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This is a unit test to check the validity of the input passed.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
class UserDetailsServiceImplTest {

    public static final String EXISTING = "existing";

    // Mocking the userRepository since it's a dependency in the userDetailsService
    @Mock
    private UserRepository userRepository;

    // Inject mocks makes sure that all dependencies are correctly mocked and passed to initialize the bean.
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        MockitoAnnotations.openMocks(this);
        Assertions.assertNotNull(userRepository);
        Assertions.assertNotNull(userDetailsService);

        User user = new User();
        user.setUsername(testInfo.getDisplayName());
        user.setPassword(testInfo.getDisplayName());
        user.setEmail(testInfo.getDisplayName().concat(TestUtils.TEST_EMAIL_SUFFIX));

        Mockito.when(userRepository.findByUsername(EXISTING)).thenReturn(user);
    }

    @Test
    void testShouldReturnNullGivenNullInput() {
        Assertions.assertNull(userDetailsService.loadUserByUsername(null));
    }

    @Test
    void testShouldReturnUserGivenAnExistingUsername(TestInfo testInfo) {
        var userDetails = userDetailsService.loadUserByUsername(EXISTING);
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testInfo.getDisplayName(), userDetails.getUsername());
    }

    @Test
    void testShouldThrowExceptionForNonExistingUsername(TestInfo testInfo) {
        var username = testInfo.getDisplayName();
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }
}