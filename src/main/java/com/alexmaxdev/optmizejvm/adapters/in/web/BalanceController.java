package com.alexmaxdev.optmizejvm.adapters.in.web;

import com.alexmaxdev.optmizejvm.application.port.in.GetBalanceUseCase;
import com.alexmaxdev.optmizejvm.domain.model.Balance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class BalanceController {

        private final GetBalanceUseCase getBalanceUseCase;

        @GetMapping("/{account}/balance")
        public BalanceResponse getBalance(@PathVariable String account) {
            Balance balance = getBalanceUseCase.getBalance(account);
            return new BalanceResponse(balance.getAccount(), balance.getAmount());
        }

    @Data
    @AllArgsConstructor
    static class BalanceResponse {
        private String account;
        private Double balance;
    }
}
