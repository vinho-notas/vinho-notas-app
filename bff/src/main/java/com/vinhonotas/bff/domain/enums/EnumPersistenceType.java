package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumPersistenceType implements EnumCode {

    VERY_PERSISTENT("Muito persistente"),
    PERSISTENT("Persistente"),
    LITTLE("Pouco persistente"),
    SHORT("Curto");

    private final String code;
}
