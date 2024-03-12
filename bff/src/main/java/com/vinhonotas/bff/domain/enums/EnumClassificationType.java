package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumClassificationType implements EnumCode {

    LITTLE("Baixa"),
    MEDIUM("Média"),
    GOOD("Boa"),
    EXCELLENT("Excelente");

    private final String code;

}
