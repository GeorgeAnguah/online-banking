package com.onlinebanking.backend.service.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configures email properties.
 *
 * @author George on 8/13/2021
 * @version 1.0
 * @since 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfig {
    private String host;
    private int port;
    private String username;
    private String password;
}
