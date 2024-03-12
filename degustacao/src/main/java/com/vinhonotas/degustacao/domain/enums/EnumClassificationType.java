package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumClassificationType
 * Refere-se a Classificação
 */
@Getter
@RequiredArgsConstructor
public enum EnumClassificationType implements EnumCode {

    LITTLE("Baixa"),
    MEDIUM("Média"),
    GOOD("Boa"),
    EXCELLENT("Excelente");

    private final String code;

}
