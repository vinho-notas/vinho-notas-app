package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumAlcoholType
 * Refere-se a Tipos de Álcool
 */
@Getter
@RequiredArgsConstructor
public enum EnumAlcoholType {

    HIGH("Alto"),
    MEDIUM("Médio"),
    LOW("Baixo"),
    VERY_LOW("Muito baixo");


    private final String description;
}
