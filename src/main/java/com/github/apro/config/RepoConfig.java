package com.github.apro.config;

import com.github.apro.statistics.Statistic;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {

    @Bean
    ConcurrentMap<Long, Statistic> provideRepoMap() {
        return new ConcurrentHashMap<>();
    }
}
