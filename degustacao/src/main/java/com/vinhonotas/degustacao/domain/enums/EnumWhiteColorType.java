package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumWhiteColorType
 * Refere-se a Cor dos Vinhos Brancos
 */
@Getter
@RequiredArgsConstructor
public enum EnumWhiteColorType {

    STRAW_YELLOW("Amarelo palha"),
    GREENISH_REFLECTIONS("Amarelo palha com reflexos esverdeados"),
    GOLDEN_REFLECTIONS("Amarelo palha com reflexos dourados"),
    GOLDEN("Dourado"),
    AMBER("Âmbar");

    private final String description;
}
