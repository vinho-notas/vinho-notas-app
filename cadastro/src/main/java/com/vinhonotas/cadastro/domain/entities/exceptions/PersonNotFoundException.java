package com.vinhonotas.cadastro.domain.entities.exceptions;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String message) {
        super(message);
    }

}
