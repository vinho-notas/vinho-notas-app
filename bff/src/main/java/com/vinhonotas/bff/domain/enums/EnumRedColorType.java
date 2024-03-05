package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumRedColorType {

    VIOLET("Violáceo"),
    RUBY_VIOLET_REFLECTIONS("Rubi com reflexos violáceos"),
    RUBY("Rubi"),
    RUBY_ORANGE_REFLECTIONS("Rubi com reflexos alaranjados"),
    ORANGE("Alaranjado");

    private final String description;

}
