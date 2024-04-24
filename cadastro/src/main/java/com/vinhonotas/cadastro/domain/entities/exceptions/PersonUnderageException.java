package com.vinhonotas.cadastro.domain.entities.exceptions;

public class PersonUnderageException extends RuntimeException{

        public PersonUnderageException(String message) {
            super(message);
        }

}
