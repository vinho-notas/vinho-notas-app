package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumPersistenceType {

    VERY_PERSISTENT("Muito persistente"),
    PERSISTENT("Persistente"),
    LITTLE("Pouco persistente"),
    SHORT("Curto");

    private final String description;
}
