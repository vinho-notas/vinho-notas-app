package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumWoodType {

    VANILLA("Baunilha"),
    SAWDUST("Serragem");

    private final String description;

}
