package com.example.cleanarchitecturewithdirtyhand.account.application;

import com.example.cleanarchitecturewithdirtyhand.account.application.port.in.SendMoneyCommand;
import com.example.cleanarchitecturewithdirtyhand.account.application.port.in.SendMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {
    @Override
    public boolean sendMoney(SendMoneyCommand sendMoneyCommand) {
        return false;
    }
}
