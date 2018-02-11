package com.github.apro.statistics;

import com.github.apro.config.AppConstants;
import com.github.apro.transactions.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Apro.
 */
@Repository
@RequiredArgsConstructor
public class InMemStatisticsRepository implements StatisticsRepository {

    private final ConcurrentMap<Long, Statistic> statsMap;
    private final Lock lock = new ReentrantLock();

    @Override
    public void add(@NotNull Transaction transaction) {
        final long timeStamp = Instant.ofEpochMilli(transaction.getTimestamp()).getEpochSecond();

        lock.lock();
        try {
            final Statistic statistic = statsMap.getOrDefault(timeStamp,
                    new Statistic(0, 0, 0, 0));
            statsMap.put(timeStamp, statistic.add(transaction));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(final long secondStamp) {
        statsMap.remove(secondStamp);
    }

    @Override
    public Statistic getMinuteStats() {
        Statistic stats = new Statistic(0, 0, 0, 0);
        final long startEpochSec =
                Instant.now().minusSeconds(AppConstants.SECS_IN_MIN - 1).getEpochSecond();
        for (int i = 0; i < AppConstants.SECS_IN_MIN; i++) {
            final long key = startEpochSec + i;
            if (statsMap.containsKey(key)) {
                stats = stats.add(statsMap.get(key));
            }
        }
        return stats;
    }
}
