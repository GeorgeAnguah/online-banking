package com.onlinebanking.config;

import com.onlinebanking.backend.persistent.domain.base.OnlineBankingAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This class holds JPA configuration settings for this application.
 *
 * @author Matthew Puentes on 6/26/2021
 * @version 1.0
 * @since 1.0
 */
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
@EnableTransactionManagement
public class JpaConfig {

    /**
     * AuditorAware bean used for auditing.
     *
     * @return Application implementation of AuditorAware.
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new OnlineBankingAuditorAware();
    }
}
