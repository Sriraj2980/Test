package com.eventledger.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private Long id;

    private Instant createdAt;

    protected Account() {
    }

    public Account(Long id) {
        this.id = id;
        this.createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
