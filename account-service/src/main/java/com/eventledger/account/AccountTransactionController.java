package com.eventledger.account;

import com.eventledger.account.dto.BalanceResponse;
import com.eventledger.account.dto.TransactionRequest;
import com.eventledger.account.dto.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts/{accountId}")
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;

    public AccountTransactionController(AccountTransactionService accountTransactionService) {
        this.accountTransactionService = accountTransactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> createTransaction(
            @PathVariable Long accountId,
            @Valid @RequestBody TransactionRequest request
    ) {
        Transaction transaction = accountTransactionService.applyTransaction(accountId, request.type(), request.amount());
        BigDecimal balance = accountTransactionService.getBalance(accountId);

        TransactionResponse body = new TransactionResponse(
                transaction.getId(),
                accountId,
                transaction.getType(),
                transaction.getAmount(),
                transaction.getCreatedAt(),
                balance
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/balance")
    public BalanceResponse getBalance(@PathVariable Long accountId) {
        return new BalanceResponse(accountId, accountTransactionService.getBalance(accountId));
    }

}
