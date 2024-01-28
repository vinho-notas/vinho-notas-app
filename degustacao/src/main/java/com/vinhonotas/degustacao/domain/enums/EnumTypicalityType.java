package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumTypicalityType
 * Refere-se a Tipicidade
 */
@Getter
@RequiredArgsConstructor
public enum EnumTypicalityType {

    VERY_TYPICAL("Muito típico"),
    TYPICAL("Típico"),
    NOT_TYPICAL("Pouco típico"),
    ATYPICAL("Atípico");

    private final String description;
}
