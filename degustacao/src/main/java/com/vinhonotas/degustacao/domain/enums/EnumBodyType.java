package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumBodyType
 * Refere-se ao Corpo
 */
@Getter
@RequiredArgsConstructor
public enum EnumBodyType {

    FULL_BODIED("Encorpado"),
    MEDIUM_BODY("Médio corpo"),
    LITTLE_BODY("Pouco corpo"),
    LIGHT("Leve");

    private final String description;
}
