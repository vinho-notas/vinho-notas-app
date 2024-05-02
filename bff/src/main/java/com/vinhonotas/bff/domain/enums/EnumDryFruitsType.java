package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumDryFruitsType implements EnumCode {

    PLUM("Ameixa"),
    ALMODN("Amêndoa"),
    HAZELNUT("Avelã"),
    BRUNETTE("Castanha"),
    NUT("Noz"),
    RAISINS("Uva passa");

    private final String code;

}
