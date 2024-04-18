package com.vinhonotas.bff.domain.entities.exceptions;

public class TastingCardNotFoundException extends RuntimeException {

    public TastingCardNotFoundException(String message) {
        super(message);
    }

}
