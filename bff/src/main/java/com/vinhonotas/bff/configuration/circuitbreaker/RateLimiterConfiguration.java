package com.vinhonotas.bff.configuration.circuitbreaker;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimiterConfiguration {

    private static final String RATE_LIMITER = "rateLimiter";

    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(20))
                .limitForPeriod(5)
                .timeoutDuration(Duration.ofSeconds(15))
                .build();
        return RateLimiterRegistry.of(config);
    }

    @Bean
    public RateLimiter rateLimiter(RateLimiterRegistry rateLimiterRegistry) {
        return rateLimiterRegistry.rateLimiter(RATE_LIMITER);
    }

}
