package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumSweetnessType
 * Refere-se a Doçura
 */
@Getter
@RequiredArgsConstructor
public enum EnumSweetnessType implements EnumCode{

    VERY_SWEET("Muito doce"),
    SWEET("Doce"),
    DRY("Seco"),
    VERY_DRY("Muito seco");

    private final String code;

}
