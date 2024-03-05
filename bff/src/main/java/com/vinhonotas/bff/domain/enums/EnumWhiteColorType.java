package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
