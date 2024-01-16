package com.vinhonotas.cadastro.domain.entities;

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
