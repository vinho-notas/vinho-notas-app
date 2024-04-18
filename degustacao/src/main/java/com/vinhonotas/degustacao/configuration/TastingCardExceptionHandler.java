package com.vinhonotas.degustacao.configuration;

import com.vinhonotas.degustacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.exceptions.TastingCardNotFoundException;
import com.vinhonotas.degustacao.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TastingCardExceptionHandler {

    @ExceptionHandler(TastingCardNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleTastingCardNotFoundException(TastingCardNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }


}
