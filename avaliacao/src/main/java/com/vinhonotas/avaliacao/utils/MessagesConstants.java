package com.vinhonotas.avaliacao.utils;

public class MessagesConstants {

    public static final String ERROR_CREATE_POINT_SCALE = "Erro ao criar a avaliação";
    public static final String ERROR_POINT_SCALE_NOT_FOUND = "Nenhuma avaliação encontrada";
    public static final String ERROR_UPDATE_POINT_SCALE = "Erro ao atualizar a avaliação";
    public static final String ERROR_DELETE_POINT_SCALE = "Erro ao deletar a avaliação";

    private MessagesConstants() {

    }

    public static MessagesConstants createMessagesConstants() {
        return new MessagesConstants();
    }
}
