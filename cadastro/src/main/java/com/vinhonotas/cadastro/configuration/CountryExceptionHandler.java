package com.vinhonotas.cadastro.configuration;

import com.vinhonotas.cadastro.domain.entities.exceptions.CountryAlreadyExistsException;
import com.vinhonotas.cadastro.domain.entities.exceptions.CountryNotFoundException;
import com.vinhonotas.cadastro.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CountryExceptionHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCountryNotFoundException(CountryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(CountryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCountryAlreadyExistsException(CountryAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }


}
