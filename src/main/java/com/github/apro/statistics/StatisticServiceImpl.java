package com.github.apro.statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticsRepository repository;

    @Override
    public Statistic getStats() {
        final Statistic statistic = repository.getMinuteStats();
        log.info("Computed statistic - {}", statistic);
        return statistic;
    }
}
