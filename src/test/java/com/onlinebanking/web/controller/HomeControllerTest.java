package com.onlinebanking.web.controller;

import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.constant.AccountConstants;
import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.shared.util.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit Test HomeController.
 *
 * @author George on 7/12/2021
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private HomeController homeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    void shouldReturnIndexViewName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(HomeConstants.INDEX_URL_MAPPING))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.INDEX_VIEW_NAME));
    }

    @Test
    void returnRedirectToAccountOverviewWhenAuthenticated(TestInfo testInfo) throws Exception {
        TestUtils.setAuthentication(TestUtils.ROLE_CUSTOMER, testInfo.getDisplayName());

        mockMvc.perform(MockMvcRequestBuilders.get(HomeConstants.INDEX_URL_MAPPING))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.REDIRECT_TO_ACCOUNT_OVERVIEW));
    }

    @Test
    void returnIndexViewNameWhenAnonymousAuthenticated() throws Exception {
        TestUtils.setAuthentication(TestUtils.ANONYMOUS_ROLE, TestUtils.ANONYMOUS_USER);

        mockMvc.perform(MockMvcRequestBuilders.get(HomeConstants.INDEX_URL_MAPPING))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.INDEX_VIEW_NAME));
    }

    @Test
    void shouldReturnAccountOverviewViewName(TestInfo testInfo) throws Exception {
        var userDto = UserUtils.createUserDto(testInfo.getDisplayName());
        userDto.setCheckingAccount(new CheckingAccount());
        userDto.setSavingsAccount(new SavingsAccount());
        Mockito.when(userService.findByUsername(ArgumentMatchers.anyString())).thenReturn(userDto);

        var userDetails = UserDetailsBuilder.buildUserDetails(UserUtils.createUser());
        var principal = new TestingAuthenticationToken(userDetails, null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(HomeConstants.ACCOUNT_OVERVIEW_URL_MAPPING)
                        .principal(principal))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.ACCOUNT_OVERVIEW_NAME))
                .andExpect(MockMvcResultMatchers.model().attributeExists(AccountConstants.SAVINGS_ACCOUNT_MODEL_KEY))
                .andExpect(MockMvcResultMatchers.model().attributeExists(AccountConstants.CHECKING_ACCOUNT_MODEL_KEY));
    }
}