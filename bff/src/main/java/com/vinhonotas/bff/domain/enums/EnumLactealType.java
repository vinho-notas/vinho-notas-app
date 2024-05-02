package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumLactealType implements EnumCode {

    BUTTER("Manteiga"),
    YOGURT("Iogurte"),
    MILK("Leite");

    private final String code;

}
