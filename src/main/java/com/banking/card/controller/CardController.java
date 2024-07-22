package com.banking.card.controller;

import com.banking.card.entity.Card;
import com.banking.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /*@PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }*/

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @PutMapping("/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card card) {
        return cardService.updateCard(id, card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    @PostMapping
    public ResponseEntity<Card> createCard2(@RequestParam String email,
                                            @RequestParam String cardType) {
        Card card = cardService.createCardWithType(email, cardType);
        return ResponseEntity.ok(card);
    }
}
