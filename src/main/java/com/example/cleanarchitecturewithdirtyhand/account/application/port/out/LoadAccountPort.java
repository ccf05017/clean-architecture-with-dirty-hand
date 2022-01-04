package com.example.cleanarchitecturewithdirtyhand.account.application.port.out;

import com.example.cleanarchitecturewithdirtyhand.account.domain.Account;

import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(Account.AccountId accountId, LocalDateTime requestDateTime);

}
