package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.PersonConverter;
import com.vinhonotas.cadastro.application.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonConverter personConverter;
}
