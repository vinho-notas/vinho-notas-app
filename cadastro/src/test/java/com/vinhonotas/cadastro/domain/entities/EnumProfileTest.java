package com.vinhonotas.cadastro.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumProfileTest {

    @Test
    void testEnumProfile() {
        EnumProfile enumProfile = EnumProfile.OENOPHILE;
        assertEquals("Enófilo", enumProfile.getCode());
        enumProfile = EnumProfile.SOMMELIER;
        assertEquals("Sommelier", enumProfile.getCode());
        enumProfile = EnumProfile.PARTNER;
        assertEquals("Parceiro", enumProfile.getCode());
    }

}
