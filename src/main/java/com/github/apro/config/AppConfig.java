package com.github.apro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@EnableAsync
class AppConfig {

    @Bean
    TaskScheduler getScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    AsyncTaskExecutor taskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
