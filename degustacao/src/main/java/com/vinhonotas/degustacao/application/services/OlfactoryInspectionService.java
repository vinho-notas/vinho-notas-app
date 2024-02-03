package com.vinhonotas.degustacao.application.services;

import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.OlfactoryInspectionInputDTO;

import java.util.List;
import java.util.UUID;

public interface OlfactoryInspectionService {

    OlfactoryInspectionEntity create(OlfactoryInspectionInputDTO olfactoryInspectionInputDTO);
    List<OlfactoryInspectionEntity> getAll();
    OlfactoryInspectionEntity getById(UUID id);
    OlfactoryInspectionEntity update(UUID id, OlfactoryInspectionInputDTO olfactoryInspectionInputDTO);
    void delete(UUID id);

}
