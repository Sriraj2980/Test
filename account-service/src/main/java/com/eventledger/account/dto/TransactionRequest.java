package com.eventledger.account.dto;

import com.eventledger.account.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(
        @NotNull TransactionType type,
        @NotNull @Positive BigDecimal amount
) {
}
