package com.github.apro.transactions;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @NotNull private Double amount;

    @Min(0)
    @NotNull
    private Long timestamp;
}
