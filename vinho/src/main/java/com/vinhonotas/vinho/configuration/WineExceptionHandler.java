package com.vinhonotas.vinho.configuration;

import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.exceptions.WineNotFoundException;
import com.vinhonotas.vinho.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WineExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(WineNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleWineNotFoundException(WineNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

}
