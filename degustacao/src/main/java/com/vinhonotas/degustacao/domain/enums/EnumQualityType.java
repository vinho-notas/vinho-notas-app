package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumQualityType
 * Refere-se a Qualidade do Vinho
 */
@Getter
@RequiredArgsConstructor
public enum EnumQualityType {

    VERY_THIN("Muito fino"),
    THIN("Fino"),
    COMMON("Comum"),
    RUDE("Grosseiro");

    private final String description;
}
