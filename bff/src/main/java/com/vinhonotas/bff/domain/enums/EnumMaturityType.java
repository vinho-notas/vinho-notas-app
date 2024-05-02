package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumMaturityType implements EnumCode {

    YOUNG("Jovem"),
    READY("Pronto"),
    MATURE("Maduro"),
    OLD("Velho");


    private final String code;

}
