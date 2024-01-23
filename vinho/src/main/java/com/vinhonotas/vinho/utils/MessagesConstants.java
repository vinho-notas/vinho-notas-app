package com.vinhonotas.vinho.utils;

public class MessagesConstants {

    public static final String ERROR_CREATE_WINE = "Erro ao salvar vinho";
    public static final String ERROR_WINE_NOT_FOUND = "Nenhum vinho encontrado";
    public static final String ERROR_UPDATE_WINE_DATA = "Erro ao atualizar dados do vinho";
    public static final String ERROR_DELETE_WINE = "Erro ao deletar vinho";

    private MessagesConstants() {
    }

    public static MessagesConstants createMessagesConstants() {
        return new MessagesConstants();
    }
}
