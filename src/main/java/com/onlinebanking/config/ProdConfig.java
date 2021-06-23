package com.onlinebanking.config;

import com.onlinebanking.constant.ProfileTypeConstants;
import org.springframework.context.annotation.Profile;

/**
 * This class provides every bean, and other configurations needed
 * to be used in the production phase.
 *
 * @author Matthew Puentes on 6/22/2021
 * @version 1.0
 * @since 1.0
 */
@Profile(ProfileTypeConstants.DEV)
public class ProdConfig {
}
