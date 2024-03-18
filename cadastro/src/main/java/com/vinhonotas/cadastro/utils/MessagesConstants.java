package com.vinhonotas.cadastro.utils;

public class MessagesConstants {

    //STATE
    public static final String ERROR_WHEN_SAVING_STATE = "Erro ao gravar dados do estado";
    public static final String STATE_NOT_FOUND = "Estado não encontrado";
    public static final String STATE_NOT_FOUND_WITH_NAME = "Estado não encontrado com o nome: ";
    public static final String STATE_NOT_FOUND_WITH_UF = "Estado não encontrado com a UF: ";
    public static final String ERROR_UPDATE_STATE_DATA = "Erro ao atualizar dados do estado";
    public static final String ERROR_DELETE_STATE_DATA = "Erro ao deletar estado";
    public static final String STATE_ALREADY_EXISTS = "Já existe um estado cadastrado com os parâmetros informados";
    public static final String STATES_NOT_FOUND = "Nenhum estado encontrado";

    //ADDRESS
    public static final String ERROR_WHEN_SAVING_ADDRESS = "Erro ao salvar endereço";
    public static final String ADDRESS_NOT_FOUND = "Nenhum endereço encontrado";
    public static final String ERROR_UPDATE_ADDRESS_DATA = "Erro ao atualizar endereço";
    public static final String ERROR_DELETE_ADDRESS_DATA = "Erro ao deletar endereço";

    //PERSON
    public static final String ERROR_WHEN_SAVING_PERSON = "Erro ao gravar dados da pessoa";
    public static final String PERSON_NOT_FOUND = "Pessoa não encontrado";
    public static final String PERSON_NOT_FOUND_WITH_NAME = "Pessoa não encontrada com o nome: ";
    public static final String ERROR_UPDATE_PERSON_DATA = "Erro ao atualizar dados da pessoa";
    public static final String ERROR_DELETE_PERSON_DATA = "Erro ao deletar pessoa";
    public static final String PERSONS_NOT_FOUND = "Nenhuma pessoa encontrada";
    public static final String PERSON_ALREADY_EXISTS = "Já existe uma pessoa cadastrada com os parâmetros informados";

    //USER
    public static final String ERROR_WHEN_SAVING_USER = "Erro ao gravar dados do usuário";
    public static final String USER_NOT_FOUND = "Usuário não encontrado";
    public static final String USER_NOT_FOUND_WITH_NAME = "Usuário não encontrado com o nome: ";
    public static final String ERROR_UPDATE_USER_DATA = "Erro ao atualizar dados do usuário";
    public static final String ERROR_DELETE_USER_DATA = "Erro ao deletar usuário";
    public static final String USER_ALREADY_EXISTS = "Já existe um usuário cadastrado com os parâmetros informados";
    public static final String USERS_NOT_FOUND = "Nenhum usuário encontrado";

    //COUNTRY
    public static final String ERROR_WHEN_SAVING_COUNTRY = "Erro ao gravar dados do país";
    public static final String COUNTRY_NOT_FOUND = "País não encontrado";
    public static final String COUNTRY_NOT_FOUND_WITH_NAME = "País não encontrado com o nome: ";
    public static final String COUNTRY_NOT_FOUND_WITH_CONTINENT = "País não encontrado com o continente: ";
    public static final String ERROR_UPDATE_COUNTRY_DATA = "Erro ao atualizar dados do país";
    public static final String ERROR_DELETE_COUNTRY_DATA = "Erro ao deletar país";
    public static final String COUNTRIES_NOT_FOUND = "Nenhum país encontrado";
    public static final String COUNTRY_ALREADY_EXISTS = "Já existe um país cadastrado com os parâmetros informados";

    //CONTROLLER
    public static final String INVALID_FIELDS = "A requisição possui campos inválidos, verifique";
    public static final String COUNTRY_NOT_FOUND_WITH_ID = "País não encontrado com o id: ";
    public static final String STATE_NOT_FOUND_WITH_ID = "Estado não encontrado com o id: ";

    private MessagesConstants() {
    }

    public static MessagesConstants createMessagesConstants() {
        return new MessagesConstants();
    }
}
