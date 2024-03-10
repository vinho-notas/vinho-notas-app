package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumProfile {

    OENOPHILE("Enófilo"),
    SOMMELIER("Sommelier"),
    PARTNER("Parceiro");

    private final String code;

}
