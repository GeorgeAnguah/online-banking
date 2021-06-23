package com.onlinebanking.config;

import com.onlinebanking.constant.ProfileTypeConstants;
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
}
