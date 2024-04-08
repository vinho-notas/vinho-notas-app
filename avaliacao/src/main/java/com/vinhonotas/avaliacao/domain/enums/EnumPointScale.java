package com.vinhonotas.avaliacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumPointScale implements EnumCode {

    CLASSIC("Um vinho incrível, sem falhas e prazeroso"),
    OUTSTANDING("Vinho excelente, com qualidade superior ao esperado"),
    VERYGOOD("Bom, possui qualidades muito interessantes e o vinho tem potencial"),
    GOOD("Vinho bem feito"),
    MEDIOCRE("Satisfatório, com falhas pequenas"),
    NOTRECOMMENDED("Não recomendado");

    private final String code;
}
