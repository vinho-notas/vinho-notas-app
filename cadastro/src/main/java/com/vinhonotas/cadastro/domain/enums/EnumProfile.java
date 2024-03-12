package com.vinhonotas.cadastro.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumProfile implements EnumCode {
    OENOPHILE("Enófilo"),
    SOMMELIER("Sommelier"),
    PARTNER("Parceiro");

    private final String code;
}
