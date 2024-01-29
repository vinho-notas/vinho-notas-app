package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumMaturityType
 * Refere-se a Maturação
 */
@Getter
@RequiredArgsConstructor
public enum EnumMaturityType {

    YOUNG("Jovem"),
    READY("Pronto"),
    MATURE("Maduro"),
    OLD("Velho");


    private final String description;
}
