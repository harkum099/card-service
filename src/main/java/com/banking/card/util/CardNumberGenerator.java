package com.banking.card.util;

import java.security.SecureRandom;

public class CardNumberGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    public enum AccountType {
        SAVINGS, CHECKING, BUSINESS
    }

    public static String generateCardNumber(AccountType accountType) {
        String prefix = getPrefixForAccountType(accountType);
        int length = 16; // Typical length for a card number

        StringBuilder cardNumber = new StringBuilder(prefix);

        // Generate the remaining digits
        while (cardNumber.length() < length - 1) {
            cardNumber.append(RANDOM.nextInt(10));
        }

        // Calculate the check digit
        cardNumber.append(calculateCheckDigit(cardNumber.toString()));

        return cardNumber.toString();
    }

    private static String getPrefixForAccountType(AccountType accountType) {
        return switch (accountType) {
            case SAVINGS -> "4";   // Prefix for Visa
            case CHECKING -> "5";  // Prefix for MasterCard
            case BUSINESS -> "6";  // Prefix for Discover
        };
    }

    private static int calculateCheckDigit(String number) {
        int sum = 0;
        boolean alternate = true;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }

    public static void main(String[] args) {
        // Example usage
        System.out.println("Visa Card Number: " + generateCardNumber(AccountType.SAVINGS));
        System.out.println("MasterCard Number: " + generateCardNumber(AccountType.CHECKING));
        System.out.println("Discover Card Number: " + generateCardNumber(AccountType.BUSINESS));
    }
}