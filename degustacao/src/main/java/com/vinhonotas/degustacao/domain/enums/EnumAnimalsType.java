package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumAnimalsType
 * Refere-se a Aromas Animais
 */
@Getter
@RequiredArgsConstructor
public enum EnumAnimalsType {

    HUNTING("Caça"),
    LEATHER("Couro");

    private final String description;
}
