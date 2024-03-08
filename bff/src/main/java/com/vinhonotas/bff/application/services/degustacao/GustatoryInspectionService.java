package com.vinhonotas.bff.application.services.degustacao;

import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.GustatoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.GustatoryInspectionOutputDTO;

import java.util.List;

public interface GustatoryInspectionService {

    GustatoryInspectionOutputDTO createGustatoryInspection(GustatoryInspectionInputDTO gustatoryInspectionInputDTO);

    List<GustatoryInspectionOutputDTO> getAllGustatoryInspections();

    GustatoryInspectionOutputDTO getGustatoryInspectionById(String id);

    GustatoryInspectionOutputDTO updateGustatoryInspection(String id, GustatoryInspectionInputDTO gustatoryInspectionInputDTO);

    void deleteGustatoryInspection(String id);

}
