package com.vinhonotas.cadastro.domain.entities.exceptions;

public class CountryNotFoundException extends RuntimeException {

        public CountryNotFoundException(String message) {
            super(message);
        }
}
