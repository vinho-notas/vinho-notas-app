package com.vinhonotas.avaliacao.application.converters;

import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PointScaleConverter {

    public PointScaleEntity toEntity(PointScaleInputDTO pointScaleInputDTO) {
        return PointScaleEntity.builder()
                .whatTasted(pointScaleInputDTO.getWhatTasted())
                .whenTasted(pointScaleInputDTO.getWhenTasted())
                .whatSaw(pointScaleInputDTO.getWhatSaw())
                .whatAromas(pointScaleInputDTO.getWhatAromas())
                .whatFlavors(pointScaleInputDTO.getWhatFlavors())
                .whatOpinion(pointScaleInputDTO.getWhatOpinion())
                .pointScale(pointScaleInputDTO.getPointScale())
                .build();
    }

    public PointScaleEntity toEntityUpdate(PointScaleInputDTO pointScaleInputDTO, UUID id, PointScaleEntity pointScaleEntity) {
        return PointScaleEntity.builder()
                .id(id)
                .whatTasted(pointScaleInputDTO.getWhatTasted() != null ? pointScaleInputDTO.getWhatTasted() : pointScaleEntity.getWhatTasted())
                .whenTasted(pointScaleInputDTO.getWhenTasted() != null ? pointScaleInputDTO.getWhenTasted() : pointScaleEntity.getWhenTasted())
                .whatSaw(pointScaleInputDTO.getWhatSaw() != null ? pointScaleInputDTO.getWhatSaw() : pointScaleEntity.getWhatSaw())
                .whatAromas(pointScaleInputDTO.getWhatAromas() != null ? pointScaleInputDTO.getWhatAromas() : pointScaleEntity.getWhatAromas())
                .whatFlavors(pointScaleInputDTO.getWhatFlavors() != null ? pointScaleInputDTO.getWhatFlavors() : pointScaleEntity.getWhatFlavors())
                .whatOpinion(pointScaleInputDTO.getWhatOpinion() != null ? pointScaleInputDTO.getWhatOpinion() : pointScaleEntity.getWhatOpinion())
                .pointScale(pointScaleInputDTO.getPointScale() != null ? pointScaleInputDTO.getPointScale() : pointScaleEntity.getPointScale())
                .build();
    }
}
