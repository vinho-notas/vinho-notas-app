package com.vinhonotas.vinho.domain.enums;

import com.vinhonotas.vinho.application.services.exceptions.BadRequestException;
import com.vinhonotas.vinho.utils.MessagesConstants;
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
