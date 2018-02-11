package com.github.apro.config;

import com.github.apro.statistics.Statistic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
class RepoConfig {

    @Bean
    ConcurrentMap<Long, Statistic> provideRepoMap() {
        return new ConcurrentHashMap<>();
    }
}
