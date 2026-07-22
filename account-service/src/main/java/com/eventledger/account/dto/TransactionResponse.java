package com.eventledger.account.dto;

import com.eventledger.account.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse(
        Long id,
        Long accountId,
        TransactionType type,
        BigDecimal amount,
        Instant createdAt,
        BigDecimal balanceAfter
) {
}
