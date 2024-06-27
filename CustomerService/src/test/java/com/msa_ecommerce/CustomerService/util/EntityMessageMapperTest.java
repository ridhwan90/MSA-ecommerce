package com.msa_ecommerce.CustomerService.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.msa_ecommerce.CustomerService.entity.Address;
import com.msa_ecommerce.CustomerService.entity.PaymentCard;
import com.msa_ecommerce.CustomerService.proto.CustomerAddress;
import com.msa_ecommerce.CustomerService.proto.CustomerCard;

public class EntityMessageMapperTest {
    @Test
    void testToCustomerAddress() {
        Address address = Address.builder()
                .addressLine1("123 Main Street")
                .addressLine2("Apt 1")
                .city("New York")
                .state("NY")
                .country("USA")
                .postalCode("10001")
                .build();

        CustomerAddress customerAddress = EntityMessageMapper.toCustomerAddress(address);

        assertEquals("123 Main Street", customerAddress.getAddressLine1());
        assertEquals("Apt 1", customerAddress.getAddressLine2());
        assertEquals("New York", customerAddress.getCity());
        assertEquals("NY", customerAddress.getState());
        assertEquals("USA", customerAddress.getCountry());
        assertEquals("10001", customerAddress.getPostalCode());
    }

    @Test
    void testToCustomerCard() {
        PaymentCard paymentCard = PaymentCard.builder()
                .cardNumber("1234567890123456")
                .cardHolderName("John Doe")
                .expiryDate("2022-12-31")
                .cvv("123")
                .address(Address.builder()
                        .addressLine1("123 Main Street")
                        .addressLine2("Apt 1")
                        .city("New York")
                        .state("NY")
                        .country("USA")
                        .postalCode("10001")
                        .build())
                .build();

        List<CustomerCard> customerCards = EntityMessageMapper.toCustomerCard(List.of(paymentCard));

        assertEquals(1, customerCards.size());
        assertEquals("1234567890123456", customerCards.get(0).getCardNumber());
        assertEquals("John Doe", customerCards.get(0).getCardHolderName());
        assertEquals("2022-12-31", customerCards.get(0).getExpiryDate());
        assertEquals("123 Main Street", customerCards.get(0).getAddress().getAddressLine1());
        assertEquals("Apt 1", customerCards.get(0).getAddress().getAddressLine2());
        assertEquals("New York", customerCards.get(0).getAddress().getCity());
        assertEquals("NY", customerCards.get(0).getAddress().getState());
        assertEquals("USA", customerCards.get(0).getAddress().getCountry());
        assertEquals("10001", customerCards.get(0).getAddress().getPostalCode());
    }
}
