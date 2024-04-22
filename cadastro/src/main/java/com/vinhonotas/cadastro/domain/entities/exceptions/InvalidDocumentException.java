package com.vinhonotas.cadastro.domain.entities.exceptions;

public class InvalidDocumentException extends RuntimeException {

        public InvalidDocumentException(String message) {
            super(message);
        }
}
