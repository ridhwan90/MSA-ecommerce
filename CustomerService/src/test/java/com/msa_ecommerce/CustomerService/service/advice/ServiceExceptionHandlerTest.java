package com.msa_ecommerce.CustomerService.service.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.grpc.Status;
import com.msa_ecommerce.CustomerService.exceptions.UnknownUserException;

public class ServiceExceptionHandlerTest {
    @Test
    void testHandleUnknownEntity() {
        UnknownUserException unknownUserException = new UnknownUserException("123");
        ServiceExceptionHandler serviceExceptionHandler = new ServiceExceptionHandler();
        Status status = serviceExceptionHandler.handleUnknownEntity(unknownUserException);
        assertEquals(Status.Code.NOT_FOUND, status.getCode());
        assertEquals("User [id: 123] is not found", status.getDescription());
    }
}
