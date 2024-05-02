package com.vinhonotas.bff.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumFruityType implements EnumCode {

    PINEAPPLE("Abacaxi"),
    BLACKBERRY("Amora"),
    PLUM("Ameixa"),
    BANANA("Banana"),
    CASSIS("Cassis"),
    CHERRY("Cereja"),
    FIG("Figo"),
    RASPBERRY("Framboesa"),
    GOOSEBERRY("Groselha"),
    KIWI("Kiwi"),
    ORANGE("Laranja"),
    LEMON("Limão"),
    LITTER("Maçã"),
    MANGO("Manga"),
    PASSION_FRUIT("Maracujá"),
    WATERMELON("Melancia"),
    PEAR("Pera"),
    PEACH("Pêssego");

    private final String code;

}
