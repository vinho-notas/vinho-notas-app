package com.vinhonotas.cadastro.configuration;

import com.vinhonotas.cadastro.domain.entities.exceptions.InvalidDocumentException;
import com.vinhonotas.cadastro.domain.entities.exceptions.PersonAlreadyExistsException;
import com.vinhonotas.cadastro.domain.entities.exceptions.PersonNotFoundException;
import com.vinhonotas.cadastro.domain.entities.exceptions.PersonUnderageException;
import com.vinhonotas.cadastro.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePersonNotFoundException(PersonNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handlePersonAlreadyExistsException(PersonAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(PersonUnderageException.class)
    public ResponseEntity<ErrorResponseDTO> handlePersonUnderageException(PersonUnderageException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidDocumentException(InvalidDocumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(exception.getMessage()));
    }

}
