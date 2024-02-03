package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumRoseColorType
 * Refere-se a Cor Rosé
 */
@Getter
@RequiredArgsConstructor
public enum EnumRoseColorType {

    LIGHT_RUBY("Rubi claro"),
    PINKISH("Rosado"),
    SALMON("Salmão"),
    BROWN("Castanho");

    private final String description;
}
