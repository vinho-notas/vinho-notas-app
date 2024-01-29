package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumSpicesType
 * Refere-se a Aromas Florais
 */
@Getter
@RequiredArgsConstructor
public enum EnumFloralsType {

    ACACIA("Acácia"),
    CLOVE("Cravo"),
    HYDRANGEA("Hortência"),
    JASMINE("Jasmim"),
    LILY("Lírio"),
    ROSE("Rosa"),
    VIOLET("Violeta");

    private final String description;
}
