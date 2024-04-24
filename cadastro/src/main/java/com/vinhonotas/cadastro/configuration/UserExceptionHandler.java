package com.vinhonotas.cadastro.configuration;

import com.vinhonotas.cadastro.domain.entities.exceptions.UserAlreadyExistsException;
import com.vinhonotas.cadastro.domain.entities.exceptions.UserNotFoundException;
import com.vinhonotas.cadastro.domain.entities.exceptions.UserProfileException;
import com.vinhonotas.cadastro.interfaces.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserProfileException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserProfileException(UserProfileException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(exception.getMessage()));
    }

}
