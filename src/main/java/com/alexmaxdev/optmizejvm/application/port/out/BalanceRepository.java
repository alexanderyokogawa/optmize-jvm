package com.alexmaxdev.optmizejvm.application.port.out;

import com.alexmaxdev.optmizejvm.domain.model.Balance;

public interface BalanceRepository {
    Balance findByAccount(String account);
}
