package com.alexmaxdev.optmizejvm.application.port.in;

import com.alexmaxdev.optmizejvm.domain.model.Balance;

public interface GetBalanceUseCase {
    Balance getBalance(String account);
}
