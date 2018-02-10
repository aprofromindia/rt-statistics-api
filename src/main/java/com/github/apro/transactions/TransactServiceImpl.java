package com.github.apro.transactions;

import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactServiceImpl implements TransactService {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void save(@NotNull Transaction transaction) {
        if (Instant.ofEpochMilli(transaction.getTimestamp()).isAfter(Instant.now())) {
            eventPublisher.publishEvent(new FutureTransactionReceivedEvent(transaction));
            log.info("published transaction - {}", transaction);
        }
    }
}
