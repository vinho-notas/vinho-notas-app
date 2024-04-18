package com.vinhonotas.cadastro.domain.entities.exceptions;

public class CountryAlreadyExistsException extends RuntimeException {

        public CountryAlreadyExistsException(String message) {
            super(message);
        }
}
