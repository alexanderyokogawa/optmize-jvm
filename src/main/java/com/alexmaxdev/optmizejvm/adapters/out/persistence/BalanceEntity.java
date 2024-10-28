package com.alexmaxdev.optmizejvm.adapters.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "balance")
@Data
public class BalanceEntity {

    @Id
    private String account;

    private double amount;
}
