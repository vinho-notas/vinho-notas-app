package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumAcidityType
 * Refere-se a Acidez
 */
@Getter
@RequiredArgsConstructor
public enum EnumAcidityType implements EnumCode {

    VERY_ACIDIC("Muito ácido"),
    ADEQUATE("Adequado"),
    LITTLE_ACID("Pouco ácido"),
    INSUFFICIENT("Insuficiente");


    private final String code;

}
