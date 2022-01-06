package com.example.cleanarchitecturewithdirtyhand.account.domain;

import org.junit.jupiter.api.Test;

import static com.example.cleanarchitecturewithdirtyhand.utils.AccountFixtures.defaultAccount;
import static com.example.cleanarchitecturewithdirtyhand.utils.ActivityFixtures.defaultActivity;
import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void withdrawalTest() {
        // given
        Account.AccountId accountId = new Account.AccountId(1L);

        Money firstActivityMoney = Money.of(999L);
        Activity firstActivity = defaultActivity()
                .withTargetAccount(accountId)
                .withMoney(firstActivityMoney).build();

        Money secondActivityMoney = Money.of(1L);
        Activity secondActivity = defaultActivity()
                .withTargetAccount(accountId)
                .withMoney(secondActivityMoney).build();

        Money baseLineMoney = Money.of(555L);
        Account account = defaultAccount()
                .withAccountId(accountId)
                .withBaselineBalance(baseLineMoney)
                .withActivityWindow(new ActivityWindow(firstActivity, secondActivity))
                .build();

        // when
        boolean result = account.withdraw(baseLineMoney, new Account.AccountId(99L));

        // then
        assertThat(result).isTrue();
        assertThat(account.howManyActivities()).isEqualTo(3);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(1000L));
    }

}