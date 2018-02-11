package com.github.apro.transactions;

import lombok.Value;

@Value
public class TransactionReceivedEvent {
    private Transaction transaction;
}
