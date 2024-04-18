package com.vinhonotas.cadastro.domain.entities.exceptions;

public class StateAlreadyExistsException extends RuntimeException {

    public StateAlreadyExistsException(String message) {
        super(message);
    }

}
