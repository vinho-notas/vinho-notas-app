package com.vinhonotas.vinho.infraestructure.controller.exceptions;

import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.exceptions.WineNotFoundException;
import com.vinhonotas.vinho.infraestructure.controller.dtos.output.ErrorResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class WineExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException exception){
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(WineNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleWineNotFoundException(WineNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(ConstraintViolationException exception) {
        String messages = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(messages));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorResponseDTO> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            ErrorResponseDTO errorMessage = new ErrorResponseDTO(error.getDefaultMessage());
            errors.add(errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
