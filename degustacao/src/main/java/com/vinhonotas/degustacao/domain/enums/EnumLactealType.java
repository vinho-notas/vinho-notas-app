package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumLactealType
 * Aromas lácteos
 */
@Getter
@RequiredArgsConstructor
public enum EnumLactealType {

    BUTTER("Manteiga"),
    YOGURT("Iogurte"),
    MILK("Leite");

    private final String description;
}
