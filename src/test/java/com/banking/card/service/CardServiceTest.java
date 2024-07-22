package com.banking.card.service;

import com.banking.card.entity.Card;
import com.banking.card.exception.CardNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CardServiceTest {
    /*@Autowired
    private CardService cardService;

    @Test
    void testCreateCard() {
        Card card = new Card();
        card.setNumber("1234-5678-9012-3456");
        card.setExpiry("12/24");

        Card createdCard = cardService.createCard2(card);

        assertNotNull(createdCard);
        assertEquals("1234-5678-9012-3456", createdCard.getNumber());
    }

    @Test
    void testGetCardById() {
        Card card = new Card();
        card.setNumber("1234-5678-9012-3456");
        card.setExpiry("12/24");

        Card createdCard = cardService.createCard2(card);
        Card foundCard = cardService.getCardById(createdCard.getId());

        assertNotNull(foundCard);
        assertEquals(createdCard.getId(), foundCard.getId());
    }

    @Test
    void testUpdateCard() {
        Card card = new Card();
        card.setNumber("1234-5678-9012-3456");
        card.setExpiry("12/24");

        Card createdCard = cardService.createCard2(card);
        createdCard.setNumber("9876-5432-1098-7654");

        Card updatedCard = cardService.updateCard(createdCard.getId(), createdCard);

        assertNotNull(updatedCard);
        assertEquals("9876-5432-1098-7654", updatedCard.getNumber());
    }

    @Test
    void testDeleteCard() {
        Card card = new Card();
        card.setNumber("1234-5678-9012-3456");
        card.setExpiry("12/24");

        Card createdCard = cardService.createCard2(card);
        cardService.deleteCard(createdCard.getId());

        assertThrows(CardNotFoundException.class, () -> cardService.getCardById(createdCard.getId()));
    }*/
}
