package com.vinhonotas.degustacao.application.services;

import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.GustatoryInspectionInputDTO;

import java.util.List;
import java.util.UUID;

public interface GustatoryInspectionService {

    GustatoryInspectionEntity create(GustatoryInspectionInputDTO gustatoryInspectionInputDTO);
    List<GustatoryInspectionEntity> getAll();
    GustatoryInspectionEntity getById(UUID id);
    GustatoryInspectionEntity update(UUID id, GustatoryInspectionInputDTO gustatoryInspectionInputDTO);
    void delete(UUID id);

}
