package com.vinhonotas.bff.application.services.degustacao;

import java.util.List;

public interface OlfactoryInspectionService {

    OlfactoryInspectionOutputDTO createOlfactoryInspection(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO);

    List<OlfactoryInspectionOutputDTO> getAllOlfactoryInspections();

    OlfactoryInspectionOutputDTO getOlfactoryInspectionById(String id);

    OlfactoryInspectionOutputDTO updateOlfactoryInspection(String id, OlfactoryInspectionInputDTO olfactoryInspectionInputDTO);

    void deleteOlfactoryInspection(String id);

}
