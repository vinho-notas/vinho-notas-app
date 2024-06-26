package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumClarityType implements EnumCode {

    VERY_CLEAR("Muito límpido"),
    CLEAR("Límpido"),
    LITTLE_CLEAR("Pouco límpido"),
    CLOUDY("Turvo");

    private final String code;

}
