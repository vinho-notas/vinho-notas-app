package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumSweetsType implements EnumCode {

    JAM("Compota"),
    HONEY("Mel"),
    BULLET("Bala");

    private final String code;

}
