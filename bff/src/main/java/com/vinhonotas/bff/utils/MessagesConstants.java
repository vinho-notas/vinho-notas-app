package com.vinhonotas.bff.utils;

public class MessagesConstants {

    //COUNTRY
    public static final String COUNTRIES_NOT_FOUND = "Nenhum país encontrado";

    //STATE
    public static final String STATES_NOT_FOUND = "Nenhum estado encontrado";

    private MessagesConstants() {
    }

    public static MessagesConstants createMessagesConstants() {
        return new MessagesConstants();
    }

}
