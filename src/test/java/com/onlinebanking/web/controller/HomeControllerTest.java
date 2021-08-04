package com.onlinebanking.web.controller;

import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.constant.ProfileTypeConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles(ProfileTypeConstants.DEV)
class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Test
    void shouldReturnIndexViewName() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get(HomeConstants.INDEX_URL_MAPPING))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(HomeConstants.INDEX_VIEW_NAME));
    }
}