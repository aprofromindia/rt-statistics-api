package com.github.apro.transactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class Transaction {
    @NotNull
    private Double amount;

    @Min(0)
    @NotNull
    private Long timestamp;

    @JsonCreator
    public Transaction(@JsonProperty("amount") Double amount,
                       @JsonProperty("timestamp") Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
