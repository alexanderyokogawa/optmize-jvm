package com.alexmaxdev.optmizejvm.adapters.out.persistence;

import com.alexmaxdev.optmizejvm.domain.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBalanceRepository extends JpaRepository<BalanceEntity, String> {
    Balance findByAccount(String account);
}
