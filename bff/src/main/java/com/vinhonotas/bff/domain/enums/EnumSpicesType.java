package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumSpicesType implements EnumCode {

    LICORICE("Alcaçuz"),
    ANISE("Anis"),
    CINNAMON("Canela"),
    INDIAN_CLOVE("Cravo da Índia"),
    CUMIN("Cominho"),
    BAY("Louro"),
    MARJORAM("Manjerona"),
    BASIL("Manjericão"),
    NUTMEG("Noz moscada"),
    OREGANO("Orégano"),
    PEPPER("Pimenta"),
    RED_PEPPER("Pimentão"),
    SAGE("Sálvia"),
    THYME("Tomilho");

    private final String code;

}
