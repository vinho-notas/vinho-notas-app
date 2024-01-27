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

    Jam("Compota"),
    Honey("Mel"),
    Bullet("Bala");

    private final String description;
}
