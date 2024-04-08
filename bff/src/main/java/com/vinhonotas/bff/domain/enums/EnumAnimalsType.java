package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumAnimalsType implements EnumCode {

    HUNTING("Caça"),
    LEATHER("Couro");

    private final String code;

}
