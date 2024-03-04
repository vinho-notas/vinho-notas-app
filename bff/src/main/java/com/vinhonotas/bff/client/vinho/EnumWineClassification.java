package com.vinhonotas.bff.client.vinho;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumWineClassification {

    DRYWINE("Vinho Seco"),
    MEDIUMDRYWINE("Vinho Meio Seco"),
    DEMISECWINE("Vinho Demi Sec"),
    SWEETWINE("Vinho Doce"),
    DESSERTWINE("Vinho de Sobremesa"),
    LATEHARVESTWINE("Vinho de Colheita Tardia"),
    ICEWINE("Vinho Gelado");

    private final String code;

}
