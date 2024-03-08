package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumChemicalsAndEtherealType {

    ACETONE("Acetona"),
    SULFUR("Enxofre");

    private final String description;

}
