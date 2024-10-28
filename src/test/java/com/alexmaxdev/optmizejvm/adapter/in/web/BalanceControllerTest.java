package com.alexmaxdev.optmizejvm.adapter.in.web;

import com.alexmaxdev.optmizejvm.adapters.in.web.BalanceController;
import com.alexmaxdev.optmizejvm.application.port.in.GetBalanceUseCase;

import com.alexmaxdev.optmizejvm.domain.model.Balance;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BalanceController.class)
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBalanceUseCase getBalanceUseCase;

    @Test
    public void testGetBalance_Success() throws Exception {
        String account = "ACC12345";
        double balanceAmount = 2000.0;

        Mockito.when(getBalanceUseCase.getBalance(account)).thenReturn(Balance.builder()
            .account(account)
            .amount(balanceAmount)
            .build());

        mockMvc.perform(get("/api/accounts/{account}/balance", account)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.account", is(account)))
            .andExpect(jsonPath("$.balance", is(balanceAmount)));
    }

    @Test
    public void testGetBalance_NotFound() throws Exception {
        String account = "ACC99999";

        Mockito.when(getBalanceUseCase.getBalance(account))
            .thenThrow(new RuntimeException("Balance not found"));

        mockMvc.perform(get("/api/accounts/{account}/balance", account)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.message", is("Balance not found")));
    }
}
