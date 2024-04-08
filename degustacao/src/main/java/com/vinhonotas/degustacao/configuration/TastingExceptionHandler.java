package com.vinhonotas.degustacao.configuration;

import com.vinhonotas.degustacao.domain.entities.exceptions.TastingNotFoundException;
import com.vinhonotas.degustacao.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TastingExceptionHandler {

    @ExceptionHandler(TastingNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleTastingNotFoundException(TastingNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }
}
