package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumTypicalityType implements EnumCode {

    VERY_TYPICAL("Muito típico"),
    TYPICAL("Típico"),
    NOT_TYPICAL("Pouco típico"),
    ATYPICAL("Atípico");

    private final String code;

}
