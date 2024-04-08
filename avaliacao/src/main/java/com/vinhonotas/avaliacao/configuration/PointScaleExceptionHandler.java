package com.vinhonotas.avaliacao.configuration;

import com.vinhonotas.avaliacao.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.avaliacao.domain.entities.exceptions.PointScaleNotFoundException;
import com.vinhonotas.avaliacao.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PointScaleExceptionHandler {

    @ExceptionHandler(PointScaleNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePointScaleNotFoundException(PointScaleNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }

}
