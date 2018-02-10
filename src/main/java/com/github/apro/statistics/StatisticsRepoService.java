package com.github.apro.statistics;

import com.github.apro.config.AppConstants;
import com.github.apro.transactions.FutureTransactionReceivedEvent;
import com.github.apro.transactions.Transaction;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class StatisticsRepoService {

    private final StatisticsRepository repository;

    @Scheduled(fixedRate = AppConstants.MIN_IN_MILLI_SEC)
    void updateRepo() {
        long lastEpochMinInSecs = Instant.now().getEpochSecond() - AppConstants.SECS_IN_MIN;
        repository.remove(lastEpochMinInSecs);
    }

    @Async
    @EventListener
    public void transactionReceived(final FutureTransactionReceivedEvent event) {
        final Transaction transaction = event.getTransaction();
        repository.add(transaction);
        log.info("Transaction received - {}", transaction);
    }
}
