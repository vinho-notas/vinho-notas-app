package com.vinhonotas.cadastro.domain.entities.exceptions;

public class TokenServiceException extends RuntimeException {

    public TokenServiceException(String message) {
        super(message);
    }
}
