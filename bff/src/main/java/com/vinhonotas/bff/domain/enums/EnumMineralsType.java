package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumMineralsType implements EnumCode {

    PETROLEUM("Petróleo"),
    CHALK("Giz"),
    WET_STONE("Pedra molhada"),
    EARTH("Terra");

    private final String code;

}
