package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumRedColorType
 * Refere-se a tipos de vinhos tintos
 */
@Getter
@RequiredArgsConstructor
public enum EnumRedColorType implements EnumCode {

    VIOLET("Violáceo"),
    RUBY_VIOLET_REFLECTIONS("Rubi com reflexos violáceos"),
    RUBY("Rubi"),
    RUBY_ORANGE_REFLECTIONS("Rubi com reflexos alaranjados"),
    ORANGE("Alaranjado");

    private final String code;

}
