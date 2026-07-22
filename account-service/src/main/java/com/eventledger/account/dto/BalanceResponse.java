package com.eventledger.account.dto;

import java.math.BigDecimal;

public record BalanceResponse(
        Long accountId,
        BigDecimal balance
) {
}
