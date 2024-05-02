package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumWoodType
 * Refere-se a aromas de Madeira
 */
@Getter
@RequiredArgsConstructor
public enum EnumWoodType implements EnumCode {

    VANILLA("Baunilha"),
    SAWDUST("Serragem");

    private final String code;

}
