package com.vinhonotas.cadastro.interfaces.controllers.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ErrorObject {

    private final String message;
    private final String field;
    private final Object parameter;
}
