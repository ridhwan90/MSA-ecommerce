package com.msa_ecommerce.CustomerService.service.advice;

import com.msa_ecommerce.CustomerService.exceptions.UnknownUserException;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler(UnknownUserException.class)
    public Status handleUnknownEntity(UnknownUserException e) {
        return Status.NOT_FOUND.withDescription(e.getMessage());
    }
}
