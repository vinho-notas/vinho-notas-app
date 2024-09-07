package com.vinhonotas.vinho.v1.domain.entities.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
