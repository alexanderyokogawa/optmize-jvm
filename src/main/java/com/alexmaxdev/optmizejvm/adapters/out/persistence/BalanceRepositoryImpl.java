package com.alexmaxdev.optmizejvm.adapters.out.persistence;

import com.alexmaxdev.optmizejvm.application.port.out.BalanceRepository;
import com.alexmaxdev.optmizejvm.domain.model.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BalanceRepositoryImpl implements BalanceRepository {

    private final JpaBalanceRepository jpaBalanceRepository;

    @Override
    public Balance findByAccount(String account) {
        BalanceEntity entity = jpaBalanceRepository.findById(account)
            .orElseThrow(() -> new RuntimeException("Balance not found for account ID: " + account));
        return new Balance(entity.getAccount(), entity.getAmount());
    }
}
