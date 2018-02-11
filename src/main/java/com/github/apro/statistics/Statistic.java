package com.github.apro.statistics;

import com.github.apro.transactions.Transaction;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Min;

@Getter
@Value
public class Statistic {
    private double sum;
    private double max;
    private double min;
    @Min(0)
    private long count;

    @SuppressWarnings("unused")
    public double getAvg() {
        if (count < 1) return 0;
        else return sum / count;
    }

    @NonNull
    Statistic add(@NonNull final Statistic statistic) {
        return new Statistic(sum + statistic.getSum(),
                Math.max(max, statistic.getMax()),
                Math.min(min, statistic.getMin()),
                count + statistic.getCount());
    }

    @NonNull
    Statistic add(@NonNull final Transaction transaction) {
        return new Statistic(sum + transaction.getAmount(),
                Math.max(max, transaction.getAmount()),
                Math.min(min, transaction.getAmount()),
                count + 1);
    }
}
