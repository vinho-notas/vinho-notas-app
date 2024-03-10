package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumIntensityType {

    VERY_INTENSE("Muito intenso"),
    INTENSE("Intenso"),
    LITTLE_INTENSE("Pouco intenso"),
    WEAK("Fraco");

    private final String description;

}
