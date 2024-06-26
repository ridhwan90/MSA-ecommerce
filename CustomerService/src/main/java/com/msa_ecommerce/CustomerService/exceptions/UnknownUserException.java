package com.msa_ecommerce.CustomerService.exceptions;

public class UnknownUserException extends RuntimeException {

    private static final String MESSAGE = "User [id: %s] is not found";

    public UnknownUserException(String id) {
        super(MESSAGE.formatted(id));
    }

}
