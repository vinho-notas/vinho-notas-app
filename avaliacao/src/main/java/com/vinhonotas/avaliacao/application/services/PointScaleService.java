package com.vinhonotas.avaliacao.application.services;

import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;

import java.util.List;
import java.util.UUID;

public interface PointScaleService {

    PointScaleEntity create(PointScaleInputDTO pointScaleInputDTO);
    List<PointScaleEntity> getAll();
    PointScaleEntity getById(UUID id);
    PointScaleEntity update(UUID id, PointScaleInputDTO pointScaleInputDTO);
    void delete(UUID id);
}
