package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumClarityType
 * Refere-se a Transparência
 */
@Getter
@RequiredArgsConstructor
public enum EnumClarityType {

    VERY_CLEAR("Muito límpido"),
    CLEAR("Límpido"),
    LITTLE_CLEAR("Pouco límpido"),
    CLOUDY("Turvo");

    private final String description;
}
