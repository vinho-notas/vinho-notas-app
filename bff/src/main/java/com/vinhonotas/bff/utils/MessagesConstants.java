package com.vinhonotas.bff.utils;

public class MessagesConstants {

    //COUNTRY
    public static final String COUNTRIES_NOT_FOUND = "Nenhum país encontrado";

    //STATE
    public static final String STATES_NOT_FOUND = "Nenhum estado encontrado";

    //PERSON
    public static final String ERROR_WHEN_SAVING_PERSON = "Erro ao salvar pessoa";
    public static final String PERSONS_NOT_FOUND = "Nenhuma pessoa encontrada";
    public static final String ERROR_UPDATE_PERSON_DATA = "Erro ao atualizar dados da pessoa";
    public static final String ERROR_WHEN_DELETING_PERSON = "Erro ao deletar pessoa";

    //USER
    public static final String ERROR_WHEN_SAVING_USER = "Erro ao salvar usuário";
    public static final String USERS_NOT_FOUND = "Nenhum usuário encontrado";
    public static final String ERROR_UPDATE_USER_DATA = "Erro ao atualizar dados do usuário";
    public static final String ERROR_WHEN_DELETING_USER = "Erro ao deletar usuário";

    //ADDRESS
    public static final String ERROR_WHEN_SAVING_ADDRESS = "Erro ao salvar endereço";
    public static final String ADDRESS_NOT_FOUND = "Nenhum endereço encontrado";
    public static final String ERROR_WHEN_UPDATING_ADDRESS = "Erro ao atualizar endereço";
    public static final String ERROR_WHEN_DELETING_ADDRESS = "Erro ao deletar endereço";

    private MessagesConstants() {
    }

    public static MessagesConstants createMessagesConstants() {
        return new MessagesConstants();
    }

}
