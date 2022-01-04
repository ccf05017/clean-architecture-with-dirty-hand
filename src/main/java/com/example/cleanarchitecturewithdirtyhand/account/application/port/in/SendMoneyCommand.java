package com.example.cleanarchitecturewithdirtyhand.account.application.port.in;

import com.example.cleanarchitecturewithdirtyhand.account.domain.Account;
import com.example.cleanarchitecturewithdirtyhand.account.domain.Money;
import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public class SendMoneyCommand {

    private final Account.AccountId sourceAccountId;
    private final Account.AccountId targetAccountId;
    private final Money money;

    public SendMoneyCommand(Account.AccountId sourceAccountId, Account.AccountId targetAccountId, Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;

        requireNonNull(sourceAccountId);
        requireNonNull(targetAccountId);
        requireNonNull(money);
        requireGreaterThan(money, 0);
    }

    private void requireGreaterThan(Money money, int compareAmount) {
        if (money.isGreaterThan(Money.of(compareAmount))) {
            throw new IllegalArgumentException("송금액은 1이상이어야만 합니다.");
        }
    }
}
