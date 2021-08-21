package com.onlinebanking.config;

import com.onlinebanking.backend.persistent.domain.base.OnlineBankingAuditorAware;
import com.onlinebanking.constant.CacheConstants;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

/**
 * This class holds JPA configuration settings for this application.
 *
 * @author Matthew Puentes on 6/26/2021
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableCaching
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
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

    /**
     * Creates maps to manage the cacheable objects.
     *
     * @return the cacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache(CacheConstants.USERS),
                new ConcurrentMapCache(CacheConstants.USER_DETAILS)
        ));
        return cacheManager;
    }
}
