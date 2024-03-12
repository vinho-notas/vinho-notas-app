package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumChemicalsAndEtherealType
 * Aromas Químicos e Etéreos
 */
@Getter
@RequiredArgsConstructor
public enum EnumChemicalsAndEtherealType implements EnumCode {

    ACETONE("Acetona"),
    SULFUR("Enxofre");

    private final String code;

}
