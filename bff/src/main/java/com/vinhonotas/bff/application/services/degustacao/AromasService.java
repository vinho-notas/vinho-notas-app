package com.vinhonotas.bff.application.services.degustacao;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AromasService {

    AromasOutputDTO createAromas(AromasInputDTO aromasInputDTO);

    List<AromasOutputDTO> getAllAromas();

    AromasOutputDTO getAromasById(String id);

    AromasOutputDTO updateAromas(String id, AromasInputDTO aromasInputDTO);

    void deleteAromas(@PathVariable ("id") String id);

}
