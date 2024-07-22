package com.banking.card.client;

import com.banking.card.service.CardService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account-service")
public interface AccountClient {
    @GetMapping("/accounts//get-ac/{id}")
    List<CardService.Account> getAccountById(@PathVariable("id") Long accountId);
}

