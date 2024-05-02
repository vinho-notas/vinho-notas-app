package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumBrightnessType implements EnumCode {

    VERY_BRIGHT("Muito brilhante"),
    BRIGHT("Brilhante"),
    LITTLE_BRIGHT("Pouco brilhante"),
    OPAQUE("Opaco");

    private final String code;

}
