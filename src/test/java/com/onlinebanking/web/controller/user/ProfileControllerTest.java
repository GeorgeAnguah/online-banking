package com.onlinebanking.web.controller.user;

import com.onlinebanking.TestUtils;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.backend.service.impl.UserDetailsBuilder;
import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.constant.SystemConstants;
import com.onlinebanking.constant.user.ProfileConstants;
import com.onlinebanking.constant.user.UserConstants;
import com.onlinebanking.enums.UserHistoryType;
import com.onlinebanking.shared.util.SecurityUtils;
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
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ProfileController profileController;

    private MockMvc mockMvc;
    private String updateUrl;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
        updateUrl = String.join("/", ProfileConstants.PROFILE_MAPPING, ProfileConstants.PROFILE_UPDATE_MAPPING);

        SecurityUtils.clearAuthentication();
    }

    @Test
    void noUserShouldRedirectToLogin() throws Exception {
        Mockito.when(userService.findByUsername(ArgumentMatchers.anyString())).thenReturn(null);

        var userDetails = UserDetailsBuilder.buildUserDetails(UserUtils.createUser());
        var principal = new TestingAuthenticationToken(userDetails, null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ProfileConstants.PROFILE_MAPPING)
                        .principal(principal))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists(SystemConstants.ERROR))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist(UserConstants.USER_MODEL_KEY))
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.REDIRECT_TO_LOGIN));
    }

    @Test
    void shouldReturnProfileViewName(TestInfo testInfo) throws Exception {
        var userDto = UserUtils.createUserDto(testInfo.getDisplayName());
        Mockito.when(userService.findByUsername(ArgumentMatchers.anyString())).thenReturn(userDto);

        var userDetails = UserDetailsBuilder.buildUserDetails(UserUtils.createUser());
        var principal = new TestingAuthenticationToken(userDetails, null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(ProfileConstants.PROFILE_MAPPING)
                        .principal(principal))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists(UserConstants.USER_MODEL_KEY))
                .andExpect(MockMvcResultMatchers.view().name(ProfileConstants.USER_PROFILE_VIEW_NAME));
    }

    @Test
    void mismatchEmailShouldRedirectToLoginOnUpdate(TestInfo testInfo) throws Exception {
        var userDto = UserUtils.createUserDto(testInfo.getDisplayName());
        TestUtils.setAuthentication(TestUtils.ROLE_CUSTOMER, userDto.getUsername());

        mockMvc.perform(MockMvcRequestBuilders
                        .put(updateUrl)
                        .param(UserConstants.EMAIL, userDto.getEmail())
                        .param(UserConstants.PASSWORD, userDto.getPassword())
                        .param(UserConstants.PUBLIC_ID, userDto.getPublicId())
                        .param(UserConstants.USERNAME, userDto.getUsername())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists(SystemConstants.ERROR))
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.REDIRECT_TO_LOGIN));
    }

    @Test
    void missingRequiredFieldShouldRedirectToLogin(TestInfo testInfo) throws Exception {
        var userDto = UserUtils.createUserDto(testInfo.getDisplayName());
        TestUtils.setAuthentication(TestUtils.ROLE_CUSTOMER, userDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(updateUrl)
                        .param(UserConstants.EMAIL, userDto.getEmail())
                        .param(UserConstants.PASSWORD, userDto.getPassword())
                        .param(UserConstants.PUBLIC_ID, userDto.getPublicId())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists(SystemConstants.ERROR))
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.REDIRECT_TO_LOGIN));
    }

    @Test
    void noAuthShouldRedirectToLogin(TestInfo testInfo) throws Exception {
        var user = UserUtils.createUser(testInfo.getDisplayName());

        mockMvc.perform(MockMvcRequestBuilders
                        .put(updateUrl)
                        .param(UserConstants.EMAIL, user.getEmail())
                        .param(UserConstants.PASSWORD, user.getPassword())
                        .param(UserConstants.PUBLIC_ID, user.getPublicId())
                        .param(UserConstants.USERNAME, user.getUsername())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists(SystemConstants.ERROR))
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.REDIRECT_TO_LOGIN));
    }

    @Test
    void shouldRedirectToProfileOnUpdate(TestInfo testInfo) throws Exception {
        var user = UserUtils.createUser(testInfo.getDisplayName());
        var userDetails = UserDetailsBuilder.buildUserDetails(user);
        Mockito.when(userService.getUserDetails(ArgumentMatchers.anyString())).thenReturn(userDetails);

        var userDto = UserUtils.convertToUserDto(user);
        Mockito.when(userService.updateUser(userDto, UserHistoryType.PROFILE_UPDATE)).thenReturn(userDto);
        TestUtils.setAuthentication(TestUtils.ROLE_CUSTOMER, userDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(updateUrl)
                        .param(UserConstants.EMAIL, user.getEmail())
                        .param(UserConstants.PASSWORD, user.getPassword())
                        .param(UserConstants.PUBLIC_ID, user.getPublicId())
                        .param(UserConstants.USERNAME, user.getUsername())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeCount(0))
                .andExpect(MockMvcResultMatchers.view().name(ProfileConstants.REDIRECT_TO_PROFILE));
    }

}