package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumBodyType implements EnumCode {

    FULL_BODIED("Encorpado"),
    MEDIUM_BODY("Médio corpo"),
    LITTLE_BODY("Pouco corpo"),
    LIGHT("Leve");

    private final String code;

}
