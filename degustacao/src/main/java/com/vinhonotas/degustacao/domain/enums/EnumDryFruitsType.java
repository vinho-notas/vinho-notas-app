package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumDryFruitsType
 * Refere-se a Aromas de Frutas Secas
 */
@Getter
@RequiredArgsConstructor
public enum EnumDryFruitsType implements EnumCode {

    PLUM("Ameixa"),
    ALMODN("Amêndoa"),
    HAZELNUT("Avelã"),
    BRUNETTE("Castanha"),
    NUT("Noz"),
    RAISINS("Uva passa");

    private final String code;

}
