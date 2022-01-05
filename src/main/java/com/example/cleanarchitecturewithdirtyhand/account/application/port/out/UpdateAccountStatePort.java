package com.example.cleanarchitecturewithdirtyhand.account.application.port.out;

import com.example.cleanarchitecturewithdirtyhand.account.domain.Account;

public interface UpdateAccountStatePort {
    void updateActivities(Account account);
}
