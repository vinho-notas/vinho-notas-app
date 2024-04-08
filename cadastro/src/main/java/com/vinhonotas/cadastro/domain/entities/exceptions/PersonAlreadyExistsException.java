package com.vinhonotas.cadastro.domain.entities.exceptions;

public class PersonAlreadyExistsException extends RuntimeException {

    public PersonAlreadyExistsException(String message) {
        super(message);
    }

}
