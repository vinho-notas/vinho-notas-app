package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumViscosityType
 * Refere-se a Viscosidade
 */
@Getter
@RequiredArgsConstructor
public enum EnumViscosityType {

    VERY_VISCOUS("Muito viscoso"),
    VISCOUS("Viscoso"),
    LITTLE_VISCOUS("Pouco viscoso"),
    SLIPPERY("Escorregadio");

    private final String description;
}
