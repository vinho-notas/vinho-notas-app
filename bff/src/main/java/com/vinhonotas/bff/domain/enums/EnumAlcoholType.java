package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumAlcoholType {

    HIGH("Alto"),
    MEDIUM("Médio"),
    LOW("Baixo"),
    VERY_LOW("Muito baixo");

    private final String description;

}
