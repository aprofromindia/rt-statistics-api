package com.github.apro.statistics;

import com.github.apro.transactions.Transaction;

import javax.validation.constraints.NotNull;

public interface StatisticsRepository {
    void add(@NotNull Transaction transaction);

    void remove(final long secondStamp);

    Statistic getMinuteStats();
}
