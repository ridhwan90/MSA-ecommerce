package com.msa_ecommerce.CustomerService.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnknownUserExceptionTest {

    @Test
    public void testUnknownUserExceptionWithId() {
        UnknownUserException unknownUserException = new UnknownUserException("123");
        assertEquals("User [id: 123] is not found", unknownUserException.getMessage());
    }

}
