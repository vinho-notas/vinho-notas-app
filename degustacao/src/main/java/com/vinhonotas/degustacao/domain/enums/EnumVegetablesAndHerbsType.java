package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumVegetablesAndHerbsType
 * Refere-se a Aromas de Vegetais e Ervas
 */
@Getter
@RequiredArgsConstructor
public enum EnumVegetablesAndHerbsType implements EnumCode {

    HAY("Feno"),
    LEAF("Folha"),
    FENNEL("Funcho"),
    GRAM("Grama"),
    MINT("Hortelã");

    private final String code;

}
