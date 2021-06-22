package com.onlinebanking.config;

import org.springframework.context.annotation.Profile;

import static com.onlinebanking.constant.ProfileTypeConstants.DEV;

/**
 * This class provides every bean, and other configurations needed
 * to be used in the development phase.
 *
 * @author George on 6/22/2021
 * @version 1.0
 * @since 1.0
 */
@Profile(DEV)
public final class DevConfig {
}
