package com.example.cleanarchitecturewithdirtyhand.account.adapter.in.web;

import com.example.cleanarchitecturewithdirtyhand.account.application.port.in.SendMoneyCommand;
import com.example.cleanarchitecturewithdirtyhand.account.application.port.in.SendMoneyUseCase;
import com.example.cleanarchitecturewithdirtyhand.account.domain.Account;
import com.example.cleanarchitecturewithdirtyhand.account.domain.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    /*
        이 컨트롤러를 Account로 바꾸고, Account에 관련된 모든 API를 넣고 싶은 욕망이 생길 수 있다.
        지름길의 유혹이다. 악마의 유혹이다. 하지 말아라.
        이렇게 컨트롤러 별로 나누면 각 기능별로 컨트롤러를 구성할 수 있다.
        컨플릭트 날 확률이 줄어들고, 슈퍼 로직이 여러 기능을 오염시키는 현상을 최대한 방지할 수 있다.
     */
    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    void sendMoney(
            @PathVariable("sourceAccountId") Long sourceAccountId,
            @PathVariable("targetAccountId") Long targetAccountId,
            @PathVariable("amount") Long amount
    ) {
        SendMoneyCommand sendMoneyCommand = new SendMoneyCommand(
                new Account.AccountId(sourceAccountId),
                new Account.AccountId(targetAccountId),
                Money.of(amount)
        );

        sendMoneyUseCase.sendMoney(sendMoneyCommand);
    }

}
