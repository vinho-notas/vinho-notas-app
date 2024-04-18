package com.vinhonotas.cadastro.configuration;

import com.vinhonotas.cadastro.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.cadastro.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }

}
