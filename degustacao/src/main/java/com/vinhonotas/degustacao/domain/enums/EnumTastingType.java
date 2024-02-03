package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumTastingType {

    VERTICAL("Degustação Vertical"),
    HORIZONTAL("Degustação Horizontal"),
    THEMATIC("Degustação Temática"),
    BLIND("Degustação às Cegas"),
    PAIRING("Degustação de Harmonização"),
    COMPARATIVE("Degustação Comparativa");

    private final String description;
}
