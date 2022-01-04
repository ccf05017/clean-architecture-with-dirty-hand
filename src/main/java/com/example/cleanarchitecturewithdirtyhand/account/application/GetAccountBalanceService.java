package com.example.cleanarchitecturewithdirtyhand.account.application;

import com.example.cleanarchitecturewithdirtyhand.account.application.port.in.GetAccountBalanceQuery;
import com.example.cleanarchitecturewithdirtyhand.account.application.port.out.LoadAccountPort;
import com.example.cleanarchitecturewithdirtyhand.account.domain.Account;
import com.example.cleanarchitecturewithdirtyhand.account.domain.Money;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(Account.AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
