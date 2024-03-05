package com.vinhonotas.bff.application.services.avaliacao;

import com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao.PointScaleInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao.PointScaleOutputDTO;

import java.util.List;

public interface PointScaleService {

    PointScaleOutputDTO createPointScale(PointScaleInputDTO pointScaleInputDTO);

    List<PointScaleOutputDTO> getAllPointScale();

    PointScaleOutputDTO getPointScaleById(String id);

    PointScaleOutputDTO updatePointScale(String id, PointScaleInputDTO pointScaleInputDTO);

    void deletePointScale(String id);

}
