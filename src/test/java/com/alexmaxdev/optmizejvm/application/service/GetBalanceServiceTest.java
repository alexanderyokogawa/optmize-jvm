package com.alexmaxdev.optmizejvm.application.service;

import com.alexmaxdev.optmizejvm.application.port.out.BalanceRepository;
import com.alexmaxdev.optmizejvm.domain.model.Balance;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetBalanceServiceTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private GetBalanceService getBalanceService;

    @Test
    public void testGetBalance_Success() {
        String account = "123";
        Double amount = 1500.0;

        when(balanceRepository.findByAccount(account)).thenReturn(new Balance(account, amount));

        Balance balance = getBalanceService.getBalance(account);

        assertEquals(amount, balance.getAmount());
        verify(balanceRepository, times(1)).findByAccount(account);
    }

    @Test
    public void testGetBalance_NotFound() {
        String account = "123";

        when(balanceRepository.findByAccount(account)).thenThrow(new RuntimeException("Balance not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getBalanceService.getBalance(account);
        });

        assertEquals("Balance not found", exception.getMessage());
        verify(balanceRepository, times(1)).findByAccount(account);
    }

}
