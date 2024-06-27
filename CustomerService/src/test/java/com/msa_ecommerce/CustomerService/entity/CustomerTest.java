package com.msa_ecommerce.CustomerService.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class CustomerTest {
@Test
void testBuilder_withAllFieldsSet() {
    UUID uuid = UUID.randomUUID();
    String name = "John Doe";
    String email = "john.doe@example.com";
    String password = "password123";
    boolean isActive = true;
    boolean isAdmin = false;
    Date updatedAt = new Date();
    Address address = new Address();
    PaymentCard paymentCard = new PaymentCard();
    List<PaymentCard> paymentCards = List.of(paymentCard);


    Customer customer = Customer.builder()
            .uuid(uuid.toString())
            .name(name)
            .email(email)
            .password(password)
            .isActive(isActive)
            .isAdmin(isAdmin)
            .updatedAt(updatedAt)
            .address(address)
            .paymentCard(paymentCards)
            .build();

    assertEquals(uuid.toString(), customer.getUuid());
    assertEquals(name, customer.getName());
    assertEquals(email, customer.getEmail());
    assertEquals(password, customer.getPassword());
    assertEquals(isActive, customer.getIsActive());
    assertEquals(isAdmin, customer.getIsAdmin());
    assertEquals(updatedAt, customer.getUpdatedAt());
    assertEquals(address, customer.getAddress());
    assertEquals(paymentCards, customer.getPaymentCard());
}

@Test
void testBuilder_withNullFields() {
    Customer customer = Customer.builder()
            .build();

    assertNull(customer.getUuid());
    assertNull(customer.getName());
    assertNull(customer.getEmail());
    assertNull(customer.getPassword());
    assertFalse(customer.getIsActive() != null && customer.getIsActive());
    assertFalse(customer.getIsAdmin() != null && customer.getIsAdmin());
    assertNull(customer.getUpdatedAt());
    assertNull(customer.getAddress());
    assertNull(customer.getPaymentCard());
}

@Test
void testBuilder_withNullFields_shouldSetNullFields() {
    Customer customer = Customer.builder()
            .build();

    assertNull(customer.getUuid());
    assertNull(customer.getName());
    assertNull(customer.getEmail());
    assertNull(customer.getPassword());
    assertNull(customer.getIsActive());
    assertNull(customer.getIsAdmin());
    assertNull(customer.getUpdatedAt());
    assertNull(customer.getAddress());
    assertNull(customer.getPaymentCard());
}
}
