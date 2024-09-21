package com.vinhonotas.vinho.v1.domain.entities.exceptions;

public class WineNotFoundException extends RuntimeException {

    public WineNotFoundException(String message) {
        super(message);
    }
}
