package com.example.cleanarchitecturewithdirtyhand.account.application.port.in;

public interface SendMoneyUseCase {
    boolean sendMoney(SendMoneyCommand sendMoneyCommand);
}
