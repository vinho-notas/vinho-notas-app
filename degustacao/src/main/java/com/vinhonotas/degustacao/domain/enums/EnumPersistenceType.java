package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumPersistenceType
 * Refere-se a Tipos de Persistência
 */
@Getter
@RequiredArgsConstructor
public enum EnumPersistenceType {

    VERY_PERSISTENT("Muito persistente"),
    PERSISTENT("Persistente"),
    LITTLE("Pouco persistente"),
    SHORT("Curto");

    private final String description;
}
