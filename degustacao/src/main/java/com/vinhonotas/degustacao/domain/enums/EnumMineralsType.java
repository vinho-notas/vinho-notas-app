package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumSpicesType
 * Refere-se a Aromas de Minerais
 */
@Getter
@RequiredArgsConstructor
public enum EnumMineralsType {

    PETROLEUM("Petróleo"),
    CHALK("Giz"),
    WET_STONE("Pedra molhada"),
    EARTH("Terra");

    private final String description;
}
