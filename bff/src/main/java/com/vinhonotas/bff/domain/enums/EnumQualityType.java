package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumQualityType {

    VERY_THIN("Muito fino"),
    THIN("Fino"),
    COMMON("Comum"),
    RUDE("Grosseiro");

    private final String description;

}
