package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumEmpireumaticsType implements EnumCode {

    ROASTING("Torrefação"),
    TOASTED("Tostado"),
    SMOKED("Defumado"),
    TOBACCO("Tabaco"),
    COFEE("Café"),
    CHOCOLATE("Chocolate"),
    CARAMEL("Caramelo");

    private final String code;

}
