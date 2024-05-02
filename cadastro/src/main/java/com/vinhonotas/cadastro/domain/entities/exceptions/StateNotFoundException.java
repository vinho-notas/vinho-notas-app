package com.vinhonotas.cadastro.domain.entities.exceptions;

public class StateNotFoundException extends RuntimeException {

        public StateNotFoundException(String message) {
            super(message);
        }
}
