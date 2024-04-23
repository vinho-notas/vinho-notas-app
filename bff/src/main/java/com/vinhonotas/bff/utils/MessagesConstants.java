package com.vinhonotas.bff.utils;

public class MessagesConstants {

    public static final String INVALID_FIELDS = "Campos inválidos";
    public static final String NOT_FOUND = "Nenhum registro encontrado";
    public static final String ERROR_WHEN_DELETING = "Erro ao deletar registro";
    public static final String ERROR_WHEN_UPDATING = "Erro ao atualizar registro";
    public static final String ERROR_WHEN_SAVING = "Erro ao salvar registro";
    public static final String BAD_REQUEST = "Requisição inválida, verifique os campos informados";
    public static final String ERROR_WHEN_LOGIN = "Erro ao realizar login";
    public static final String RATE_LIMITER_MESSAGE = "Limite de requisições excedido. Tente novamente mais tarde.";

    private MessagesConstants() {
    }

    public static MessagesConstants createMessagesConstants() {
        return new MessagesConstants();
    }

}
