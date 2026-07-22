package com.eventledger.account;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountTransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountTransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction applyTransaction(Long accountId, TransactionType type, BigDecimal amount) {
        if (!accountRepository.existsById(accountId)) {
            accountRepository.save(new Account(accountId));
        }
        return transactionRepository.save(new Transaction(accountId, type, amount));
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found: " + accountId);
        }
        BigDecimal credits = transactionRepository.sumAmountByAccountIdAndType(accountId, TransactionType.CREDIT);
        BigDecimal debits = transactionRepository.sumAmountByAccountIdAndType(accountId, TransactionType.DEBIT);
        return credits.subtract(debits);
    }

}
