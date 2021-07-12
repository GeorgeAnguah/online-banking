package com.onlinebanking.controller;

import com.onlinebanking.constant.HomeConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Unit Test HomeController.
 *
 * @author George on 7/12/2021
 * @version 1.0
 * @since 1.0
 */
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Use to satisfy spring security requirement.
     */
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldReturnIndexViewName() throws Exception {
        this.mockMvc.perform(get(HomeConstants.INDEX_URL_MAPPING))
                .andExpect(status().isOk())
                .andExpect(view().name(HomeConstants.INDEX_VIEW_NAME));
    }
}