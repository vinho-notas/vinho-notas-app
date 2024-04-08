package com.vinhonotas.cadastro.domain.entities.exceptions;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String message) {
        super(message);
    }

}
