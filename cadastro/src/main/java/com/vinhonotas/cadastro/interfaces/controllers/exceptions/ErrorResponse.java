package com.vinhonotas.cadastro.interfaces.controllers.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private final String message;
    private final int code;
    private final String objectName;
    private final List<ErrorObject> errors;
}
