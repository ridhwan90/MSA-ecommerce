package com.msa_ecommerce.CustomerService.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    void testBuilder_withAllFieldsSet() {
        Address address = Address.builder()
                .addressLine1("addressLine1")
                .addressLine2("addressLine2")
                .city("city")
                .state("state")
                .country("country")
                .postalCode("postalCode")
                .build();

        assertEquals("addressLine1", address.getAddressLine1());
        assertEquals("addressLine2", address.getAddressLine2());
        assertEquals("city", address.getCity());
        assertEquals("state", address.getState());
        assertEquals("country", address.getCountry());
        assertEquals("postalCode", address.getPostalCode());
    }


    @Test
    void testBuilder_withNullFields() {
        Address address = Address.builder()
                .build();

        assertNull(address.getAddressLine1());
        assertNull(address.getAddressLine2());
        assertNull(address.getCity());
        assertNull(address.getState());
        assertNull(address.getCountry());
        assertNull(address.getPostalCode());
    }

    @Test
    void testBuilder_withNullFields_shouldSetNullFields() {
        Address address = Address.builder()
                .build(); 

        assertNull(address.getAddressLine1());
        assertNull(address.getAddressLine2());
        assertNull(address.getCity());
        assertNull(address.getState());
        assertNull(address.getCountry());
        assertNull(address.getPostalCode());
    }   
    
}
