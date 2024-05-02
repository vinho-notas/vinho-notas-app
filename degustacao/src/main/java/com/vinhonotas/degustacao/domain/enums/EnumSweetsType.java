package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumSweetsType
 * Refere-se a Aromas Adocicados
 */
@Getter
@RequiredArgsConstructor
public enum EnumSweetsType implements EnumCode {

    JAM("Compota"),
    HONEY("Mel"),
    BULLET("Bala");

    private final String code;

}
