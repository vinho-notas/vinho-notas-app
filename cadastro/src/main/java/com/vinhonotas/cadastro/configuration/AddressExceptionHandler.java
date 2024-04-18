package com.vinhonotas.cadastro.configuration;

import com.vinhonotas.cadastro.domain.entities.exceptions.AddressNotFoundException;
import com.vinhonotas.cadastro.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressExceptionHandler {

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAddressNotFoundException(AddressNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

}
