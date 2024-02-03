package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumChemicalsAndEtherealType
 * Aromas Químicos e Etéreos
 */
@Getter
@RequiredArgsConstructor
public enum EnumChemicalsAndEtherealType {

    ACETONE("Acetona"),
    SULFUR("Enxofre");

    private final String description;
}
