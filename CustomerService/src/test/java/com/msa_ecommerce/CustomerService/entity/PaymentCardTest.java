package com.msa_ecommerce.CustomerService.entity;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCardTest {

    @Test
    public void testBuilder_withAllFieldsSet() {
        String uuid = "123";
        String cardNumber = "1234567890123456";
        String cardHolderName = "John Doe";
        String expiryDate = "12/2023";
        String cvv = "123";
        Date updatedAt = new Date();
        Address address = new Address();

        PaymentCard paymentCard = PaymentCard.builder()
                .uuid(uuid)
                .cardNumber(cardNumber)
                .cardHolderName(cardHolderName)
                .expiryDate(expiryDate)
                .cvv(cvv)
                .updatedAt(updatedAt)
                .address(address)
                .build();

        assertEquals(uuid, paymentCard.getUuid());
        assertEquals(cardNumber, paymentCard.getCardNumber());
        assertEquals(cardHolderName, paymentCard.getCardHolderName());
        assertEquals(expiryDate, paymentCard.getExpiryDate());
        assertEquals(cvv, paymentCard.getCvv());
        assertEquals(updatedAt, paymentCard.getUpdatedAt());
        assertEquals(address, paymentCard.getAddress());
    }
}
