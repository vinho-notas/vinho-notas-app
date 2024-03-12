package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumFloralsType implements EnumCode {

    ACACIA("Acácia"),
    CLOVE("Cravo"),
    HYDRANGEA("Hortência"),
    JASMINE("Jasmim"),
    LILY("Lírio"),
    ROSE("Rosa"),
    VIOLET("Violeta");

    private final String code;

}
