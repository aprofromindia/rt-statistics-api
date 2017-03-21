package com.github.apro.statistics;

import com.github.apro.transactions.Transaction;
import java.time.Instant;
import java.util.concurrent.ConcurrentMap;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/** Created by achoudh on 20/03/2017. */
@Repository
@RequiredArgsConstructor
public class InMemStatisticsRepository implements StatisticsRepository {

    private final ConcurrentMap<Long, Statistic> statsMap;

    public void add(@NotNull Transaction transaction) {
        final long timeStamp = Instant.ofEpochMilli(transaction.getTimestamp()).getEpochSecond();

        Statistic statistic = statsMap.getOrDefault(timeStamp, new Statistic());
        statistic.setSum(statistic.getSum() + transaction.getAmount());
        statistic.setCount(statistic.getCount() + 1);

        if (transaction.getAmount() > statistic.getMax()) {
            statistic.setMax(transaction.getAmount());
        }

        if (transaction.getAmount() < statistic.getMax()) {
            statistic.setMin(transaction.getAmount());
        }
    }

    @Override
    public void remove(final long secondStamp) {
        statsMap.remove(secondStamp);
    }

    @Override
    public Statistic getMinuteStats() {
        Statistic stats = new Statistic();
        for (ConcurrentMap.Entry<Long, Statistic> entry : statsMap.entrySet()) {

            stats.setCount(stats.getCount() + 1);
            stats.setSum(stats.getSum() + entry.getValue().getSum());

            stats.setMax(Math.max(stats.getMax(), entry.getValue().getMax()));
            stats.setMin(Math.min(stats.getMin(), entry.getValue().getMin()));
        }
        return stats;
    }
}
