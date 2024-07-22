package com.banking.card.service;

import com.banking.card.client.AccountClient;
import com.banking.card.client.UserClient;
import com.banking.card.entity.Card;
import com.banking.card.exception.AccountNotFoundException;
import com.banking.card.exception.CardGenerationException;
import com.banking.card.exception.CardNotFoundException;
import com.banking.card.exception.UserNotFoundException;
import com.banking.card.repository.CardRepository;
import com.banking.card.util.CardNumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Autowired
    private UserClient userClient;

    @Autowired
    private AccountClient accountClient;

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    public Card updateCard(Long id, Card cardDetails) {
        Card card = getCardById(id);
        //card.setNumber(cardDetails.getNumber());
        //card.setExpiry(cardDetails.getExpiry());
        return cardRepository.save(card);
    }

    public void deleteCard(Long id) {
        Card card = getCardById(id);
        cardRepository.delete(card);
    }

    public Card createCardWithType(String email, String cardType) {
        // Verify user exists
        User user = userClient.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found, so can't create card. Create a user first.");
        }
        log.info("user found");
        // Verify account exists
        List<Account> accountList = accountClient.getAccountById(user.getId());
        if (accountList == null || accountList.size() == 0) {
            throw new AccountNotFoundException("Account not found, so can't create card.");
        }
        log.info("A/C also found");
        // Verify account type supports card generation
        Optional<CardNumberGenerator.AccountType> matchedType = accountList.stream()
                .map(account -> CardNumberGenerator.AccountType.valueOf(account.getAccountType().toUpperCase()))
                .filter(accountType -> accountType.name().equalsIgnoreCase(cardType))
                .findFirst();

        if (matchedType.isEmpty()) {
            throw new CardGenerationException("Card type is not supported for the given account type.");
        }

        CardNumberGenerator.AccountType accountType = matchedType.get();

        // Create card
        String cardNumber = CardNumberGenerator.generateCardNumber(accountType);
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setCardType(cardType);
        card.setUserId(user.getId());
        card.setAccountId(accountList.get(1).getId());

        return cardRepository.save(card);
    }

    public static class User {
        private Long id;
        private String name;
        private String email;

        public Long getId() {
            return id;
        }

        // Setter for id
        public void setId(Long id) {
            this.id = id;
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Setter for name
        public void setName(String name) {
            this.name = name;
        }

        // Getter for email
        public String getEmail() {
            return email;
        }

        // Setter for email
        public void setEmail(String email) {
            this.email = email;
        }

    }

    public static class Account {
        private Long id;
        private String accountType;
        private Double balance;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        private Long userId;

    }
}
