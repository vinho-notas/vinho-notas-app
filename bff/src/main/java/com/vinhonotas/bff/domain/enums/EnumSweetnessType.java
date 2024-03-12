package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumSweetnessType implements EnumCode {

    VERY_SWEET("Muito doce"),
    SWEET("Doce"),
    DRY("Seco"),
    VERY_DRY("Muito seco");

    private final String code;

}
