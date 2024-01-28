package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumAcidityType
 * Refere-se a Acidez
 */
@Getter
@RequiredArgsConstructor
public enum EnumAcidityType {

    VERY_ACIDIC("Muito ácido"),
    ADEQUATE("Adequado"),
    LITTLE_ACID("Pouco ácido"),
    INSUFFICIENT("Insuficiente");


    private final String description;
}
