package com.github.apro.transactions;

import com.github.apro.config.AppConstants;

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
    public boolean save(@NotNull final Transaction transaction) {
        if (Instant.ofEpochMilli(transaction.getTimestamp())
                .isAfter(Instant.now().minusSeconds(AppConstants.SECS_IN_MIN))) {
            eventPublisher.publishEvent(new TransactionReceivedEvent(transaction));
            log.info("published transaction - {}", transaction);
            return true;
        }
        return false;
    }
}
