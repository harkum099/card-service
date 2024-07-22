package com.banking.card.client;

import com.banking.card.service.CardService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/by-email/{email}")
    CardService.User getUserByEmail(@PathVariable("email") String email);
}
