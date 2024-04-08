package com.vinhonotas.degustacao.configuration;

import com.vinhonotas.degustacao.domain.entities.exceptions.AromasNotFoundException;
import com.vinhonotas.degustacao.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AromasExceptionHandler {

    @ExceptionHandler(AromasNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAromasNotFoundException(AromasNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

}
