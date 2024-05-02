package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumWineType implements EnumCode {

    REDWINE("Vinho Tinto"),
    WHITEWINE("Vinho Branco"),
    ROSEWINE("Vinho Rose"),
    SPARKLING("Vinho Espumante"),
    FORTIFIEDWINE("Vinho Fortificado"),
    SWEETWINE("Vinho Doce"),
    DESSERTWINE("Vinho de Sobremesa"),
    VINTAGEWINE("Vinho de Safra"),
    BLENDEDWINE("Vinho Assemblage"),
    NATURALWINE("Vinho Orgânico");

    private final String code;

}
