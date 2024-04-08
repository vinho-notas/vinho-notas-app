package com.vinhonotas.cadastro.configuration;

import com.vinhonotas.cadastro.domain.entities.exceptions.StateAlreadyExistsException;
import com.vinhonotas.cadastro.domain.entities.exceptions.StateNotFoundException;
import com.vinhonotas.cadastro.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StateExceptionHandler {

    @ExceptionHandler(StateAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleStateAlreadyExistsException(StateAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(StateNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleStateNotFoundException(StateNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

}
