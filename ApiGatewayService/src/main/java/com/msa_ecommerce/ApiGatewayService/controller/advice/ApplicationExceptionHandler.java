package com.msa_ecommerce.ApiGatewayService.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.grpc.StatusRuntimeException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<String> handleStatusRuntimeException(StatusRuntimeException e) {
        return switch (e.getStatus().getCode()) {
            case NOT_FOUND -> ResponseEntity.notFound().build();
            case null, default -> ResponseEntity.internalServerError().body(e.getMessage());
        };
    }

}
