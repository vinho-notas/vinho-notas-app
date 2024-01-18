package com.vinhonotas.cadastro.interfaces.dtos.controllers.exceptions;

import com.vinhonotas.cadastro.utils.MessagesConstants;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, ex.getStatusCode(), errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatusCode statusCode, List<ErrorObject> errors) {
        return new ErrorResponse(MessagesConstants.INVALID_FIELDS, statusCode.value(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}
