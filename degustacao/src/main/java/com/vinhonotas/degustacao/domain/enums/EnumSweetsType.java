package com.vinhonotas.degustacao.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * EnumSweetsType
 * Refere-se a Aromas Adocicados
 */
@Getter
@RequiredArgsConstructor
public enum EnumSweetsType {

    JAM("Compota"),
    HONEY("Mel"),
    BULLET("Bala");

    private final String description;
}
