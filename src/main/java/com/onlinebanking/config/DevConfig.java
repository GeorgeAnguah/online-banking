package com.onlinebanking.config;

import com.onlinebanking.constant.ProfileTypeConstants;
import com.onlinebanking.constant.SecurityConstants;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;



/**
 * This class provides every bean, and other configurations needed
 * to be used in the development phase.
 *
 * @author George on 6/22/2021
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Profile(ProfileTypeConstants.DEV)
public class DevConfig {

    /**
     * A bean has to register the path /h2-console for the h2 database.
     * @return the h2 registered bean.
     */
    @Bean
    public ServletRegistrationBean<WebServlet> h2servletRegistration() {
        return new ServletRegistrationBean<>(new WebServlet(), SecurityConstants.H2_CONSOLE_URL_MAPPING);
    }
}
