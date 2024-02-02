package com.vinhonotas.degustacao.application.services;

import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.VisualInspectionInputDTO;

import java.util.List;
import java.util.UUID;

public interface VisualInspectionService {

    VisualInspectionEntity create(VisualInspectionInputDTO visualInspectionInputDTO);
    List<VisualInspectionEntity> getAll();
    VisualInspectionEntity getById(UUID id);
    VisualInspectionEntity update(UUID id, VisualInspectionInputDTO visualInspectionInputDTO);
    void delete(UUID id);

}
