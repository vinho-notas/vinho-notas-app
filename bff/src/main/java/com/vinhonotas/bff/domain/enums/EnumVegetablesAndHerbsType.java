package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumVegetablesAndHerbsType {

    HAY("Feno"),
    LEAF("Folha"),
    FENNEL("Funcho"),
    GRAM("Grama"),
    MINT("Hortelã");

    private final String description;

}
