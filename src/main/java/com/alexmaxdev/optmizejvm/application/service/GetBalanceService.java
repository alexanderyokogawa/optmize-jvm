package com.alexmaxdev.optmizejvm.application.service;

import com.alexmaxdev.optmizejvm.application.port.in.GetBalanceUseCase;
import com.alexmaxdev.optmizejvm.application.port.out.BalanceRepository;
import com.alexmaxdev.optmizejvm.domain.model.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBalanceService implements GetBalanceUseCase {

    private final BalanceRepository balanceRepository;

    @Override
    public Balance getBalance(String account) {
        return balanceRepository.findByAccount(account);
    }

}
