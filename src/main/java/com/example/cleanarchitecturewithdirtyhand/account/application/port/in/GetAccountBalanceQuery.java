package com.example.cleanarchitecturewithdirtyhand.account.application.port.in;

import com.example.cleanarchitecturewithdirtyhand.account.domain.Account;
import com.example.cleanarchitecturewithdirtyhand.account.domain.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(Account.AccountId accountId);

}
