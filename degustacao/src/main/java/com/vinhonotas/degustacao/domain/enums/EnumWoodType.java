package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumWoodType
 * Refere-se a aromas de Madeira
 */
@Getter
@RequiredArgsConstructor
public enum EnumWoodType {

    VANILLA("Baunilha"),
    SAWDUST("Serragem");

    private final String description;
}
