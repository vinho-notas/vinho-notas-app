package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumTanninType implements EnumCode {

    VERY_TANNIC("Muito tânico"),
    QDEQUATE("Adequado"),
    LITTLE_TANIC("Pouco tânico"),
    ABSENT("Ausente");

    private final String code;

}
