package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumWoodType implements EnumCode {

    VANILLA("Baunilha"),
    SAWDUST("Serragem");

    private final String code;

}
