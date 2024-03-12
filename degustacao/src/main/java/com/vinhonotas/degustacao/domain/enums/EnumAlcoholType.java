package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumAlcoholType
 * Refere-se a Tipos de Álcool
 */
@Getter
@RequiredArgsConstructor
public enum EnumAlcoholType implements EnumCode {

    HIGH("Alto"),
    MEDIUM("Médio"),
    LOW("Baixo"),
    VERY_LOW("Muito baixo");


    private final String code;

}
