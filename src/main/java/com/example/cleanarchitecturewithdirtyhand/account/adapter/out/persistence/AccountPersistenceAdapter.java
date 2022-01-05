package com.example.cleanarchitecturewithdirtyhand.account.adapter.out.persistence;

import com.example.cleanarchitecturewithdirtyhand.account.application.port.out.LoadAccountPort;
import com.example.cleanarchitecturewithdirtyhand.account.application.port.out.UpdateAccountStatePort;
import com.example.cleanarchitecturewithdirtyhand.account.domain.Account;
import com.example.cleanarchitecturewithdirtyhand.account.domain.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate) {
        AccountJpaEntity accountJpaEntity = accountRepository.findById(accountId.getValue())
                .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities = activityRepository.findByOwnerSince(accountId.getValue(), baselineDate);

        Long withdrawalBalance = orZero(activityRepository
                .getWithdrawalBalanceUntil(accountId.getValue(), baselineDate));

        Long depositBalance = orZero(activityRepository.getDepositBalanceUntil(accountId.getValue(), baselineDate));

        return accountMapper.mapToDomainEntity(
                accountJpaEntity,
                activities,
                withdrawalBalance,
                depositBalance
        );
    }

    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (Objects.isNull(activity.getId())) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }

    private Long orZero(Long value) {
        return value == null ? 0L : value;
    }

}
