package com.vinhonotas.vinho.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumWineClassification implements EnumCode {

    DRYWINE("Vinho Seco"),
    MEDIUMDRYWINE("Vinho Meio Seco"),
    DEMISECWINE("Vinho Demi Sec"),
    SWEETWINE("Vinho Doce"),
    DESSERTWINE("Vinho de Sobremesa"),
    LATEHARVESTWINE("Vinho de Colheita Tardia"),
    ICEWINE("Vinho Gelado");

    private final String code;

}
