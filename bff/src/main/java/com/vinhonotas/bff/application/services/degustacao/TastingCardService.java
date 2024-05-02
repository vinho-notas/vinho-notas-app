package com.vinhonotas.bff.application.services.degustacao;

import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;

import java.util.List;

public interface TastingCardService {

    TastingCardOutputDTO createTastingCard(TastingCardInputDTO tastingCardInputDTO);

    List<TastingCardOutputDTO> getAllTastingCards();

    TastingCardOutputDTO getTastingCardById(String id);

    TastingCardOutputDTO updateTastingCard(String id, TastingCardInputDTO tastingCardInputDTO);

    void deleteTastingCard(String id);

}
