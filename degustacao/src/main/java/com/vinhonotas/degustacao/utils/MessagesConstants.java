package com.vinhonotas.degustacao.utils;

public class MessagesConstants {

    //Aromas
    public static final String ERROR_WHEN_SAVING_AROMAS = "Erro ao salvar os aromas";
    public static final String AROMAS_NOT_FOUND = "Nenhum aroma encontrado";
    public static final String ERROR_WHEN_UPDATING_AROMAS = "Erro ao atualizar os aromas";
    public static final String ERROR_WHEN_DELETING_AROMAS = "Erro ao deletar os aromas";

    //OlfactoryInspection
    public static final String ERROR_WHEN_SAVING_OLFACTORY_INSPECTION = "Erro ao salvar a inspeção olfativa";
    public static final String OLFACTORY_INSPECTION_NOT_FOUND = "Nenhuma inspeção olfativa encontrada";
    public static final String ERROR_WHEN_UPDATING_OLFACTORY_INSPECTION = "Erro ao atualizar a inspeção olfativa";
    public static final String ERROR_WHEN_DELETING_OLFACTORY_INSPECTION = "Erro ao deletar a inspeção olfativa";

    //VisualInspection
    public static final String ERROR_WHEN_SAVING_VISUAL_INSPECTION = "Erro ao salvar a inspeção visual";
    public static final String VISUAL_INSPECTION_NOT_FOUND = "Nenhuma inspeção visual encontrada";
    public static final String ERROR_WHEN_UPDATING_VISUAL_INSPECTION = "Erro ao atualizar a inspeção visual";
    public static final String ERROR_WHEN_DELETING_VISUAL_INSPECTION = "Erro ao deletar a inspeção visual";

    //GustatoryInspection
    public static final String ERROR_WHEN_SAVING_GUSTATORY_INSPECTION = "Erro ao criar a inspeção gustativa";
    public static final String GUSTATORY_INSPECTION_NOT_FOUND = "Nenhuma inspeção gustativa encontrada";
    public static final String ERROR_WHEN_UPDATING_GUSTATORY_INSPECTION = "Erro ao atualizar a inspeção gustativa";
    public static final String ERROR_WHEN_DELETING_GUSTATORY_INSPECTION = "Erro ao deletar a inspeção gustativa";

    //Tasting
    public static final String ERROR_WHEN_SAVING_TASTING = "Erro ao salvar a degustação";
    public static final String TASTING_NOT_FOUND = "Nenhuma degustação encontrada";
    public static final String ERROR_WHEN_UPDATING_TASTING = "Erro ao atualizar a degustação";
    public static final String ERROR_WHEN_DELETING_TASTING = "Erro ao deletar a degustação";

    //TastingCard
    public static final String ERROR_WHEN_SAVING_TASTING_CARD = "Erro ao salvar a ficha de degustação";
    public static final String TASTING_CARD_NOT_FOUND = "Nenhuma ficha de degustação encontrada";
    public static final String ERROR_WHEN_UPDATING_TASTING_CARD = "Erro ao atualizar a ficha de degustação";
    public static final String ERROR_WHEN_DELETING_TASTING_CARD = "Erro ao deletar a ficha de degustação";

    private MessagesConstants() {
    }

    public static MessagesConstants createMessagesConstants() {
        return new MessagesConstants();
    }

}
