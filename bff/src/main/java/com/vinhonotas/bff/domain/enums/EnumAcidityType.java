package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumAcidityType implements EnumCode {

    VERY_ACIDIC("Muito ácido"),
    ADEQUATE("Adequado"),
    LITTLE_ACID("Pouco ácido"),
    INSUFFICIENT("Insuficiente");

    private final String code;

}
