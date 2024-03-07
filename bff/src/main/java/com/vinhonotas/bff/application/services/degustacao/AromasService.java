package com.vinhonotas.bff.application.services.degustacao;

import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.AromasInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.AromasOutputDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AromasService {

    AromasOutputDTO createAromas(AromasInputDTO aromasInputDTO);

    List<AromasOutputDTO> getAllAromas();

    AromasOutputDTO getAromasById(String id);

    AromasOutputDTO updateAromas(String id, AromasInputDTO aromasInputDTO);

    void deleteAromas(@PathVariable ("id") String id);

}
