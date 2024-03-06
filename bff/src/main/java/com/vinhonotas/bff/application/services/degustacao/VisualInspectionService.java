package com.vinhonotas.bff.application.services.degustacao;

import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.VisualInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.VisualInspectionOutputDTO;

import java.util.List;

public interface VisualInspectionService {

    VisualInspectionOutputDTO createVisualInspection(VisualInspectionInputDTO visualInspectionInputDTO);

    List<VisualInspectionOutputDTO> getAllVisualInspections();

    VisualInspectionOutputDTO getVisualInspectionById(String id);

    VisualInspectionOutputDTO updateVisualInspection(String id, VisualInspectionInputDTO visualInspectionInputDTO);

    void deleteVisualInspection(String id);

}
