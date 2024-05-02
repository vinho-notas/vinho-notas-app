package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumRoseColorType implements EnumCode {

    LIGHT_RUBY("Rubi claro"),
    PINKISH("Rosado"),
    SALMON("Salmão"),
    BROWN("Castanho");

    private final String code;

}
