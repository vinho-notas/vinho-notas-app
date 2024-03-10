package com.vinhonotas.bff.application.services.degustacao;

import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.OlfactoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.OlfactoryInspectionOutputDTO;

import java.util.List;

public interface OlfactoryInspectionService {

    OlfactoryInspectionOutputDTO createOlfactoryInspection(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO);

    List<OlfactoryInspectionOutputDTO> getAllOlfactoryInspections();

    OlfactoryInspectionOutputDTO getOlfactoryInspectionById(String id);

    OlfactoryInspectionOutputDTO updateOlfactoryInspection(String id, OlfactoryInspectionInputDTO olfactoryInspectionInputDTO);

    void deleteOlfactoryInspection(String id);

}
