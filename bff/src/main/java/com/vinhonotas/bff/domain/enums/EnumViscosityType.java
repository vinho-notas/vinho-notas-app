package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumViscosityType implements EnumCode {

    VERY_VISCOUS("Muito viscoso"),
    VISCOUS("Viscoso"),
    LITTLE_VISCOUS("Pouco viscoso"),
    SLIPPERY("Escorregadio");

    private final String code;

}
