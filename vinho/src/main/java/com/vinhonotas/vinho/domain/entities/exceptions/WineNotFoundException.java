package com.vinhonotas.vinho.domain.entities.exceptions;

public class WineNotFoundException extends RuntimeException {

    public WineNotFoundException(String message) {
        super(message);
    }
}
