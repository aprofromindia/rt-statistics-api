package com.github.apro.transactions;

import lombok.Value;

@Value
public class FutureTransactionReceivedEvent {
    private Transaction transaction;
}
