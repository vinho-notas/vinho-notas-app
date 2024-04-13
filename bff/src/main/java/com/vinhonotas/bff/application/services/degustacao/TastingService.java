package com.vinhonotas.bff.application.services.degustacao;

import java.util.List;

public interface TastingService {

    TastingOutputDTO createTasting(TastingInputDTO tastingInputDTO);

    List<TastingOutputDTO> getAllTastings();

    TastingOutputDTO getTastingById(String id);

    TastingOutputDTO updateTasting(String id, TastingInputDTO tastingInputDTO);

    void deleteTasting(String id);

}
