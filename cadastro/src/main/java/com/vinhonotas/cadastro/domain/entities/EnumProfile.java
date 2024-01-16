package com.vinhonotas.cadastro.domain.entities;

public enum EnumProfile {
    OENOPHILE("Enófilo"),
    SOMMELIER("Sommelier"),
    PARTNER("Parceiro");

    private String code;

    EnumProfile(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
