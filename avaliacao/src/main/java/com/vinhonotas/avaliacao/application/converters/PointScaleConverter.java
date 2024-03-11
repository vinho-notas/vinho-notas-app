package com.vinhonotas.avaliacao.application.converters;

import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import com.vinhonotas.avaliacao.domain.enums.EnumPointScale;
import com.vinhonotas.avaliacao.interfaces.dtos.inputs.PointScaleInputDTO;
import com.vinhonotas.avaliacao.interfaces.dtos.outputs.PointScaleOutputDTO;
import com.vinhonotas.avaliacao.utils.EnumConverter;
import org.springframework.stereotype.Component;

import java.util.List;
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
                .pointScale(EnumConverter.fromString(pointScaleInputDTO.getPointScale(), EnumPointScale.class))
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
                .pointScale(pointScaleInputDTO.getPointScale() != null ? EnumConverter.fromString(pointScaleInputDTO
                        .getPointScale(), EnumPointScale.class) : pointScaleEntity.getPointScale())
                .build();
    }

    public PointScaleOutputDTO toOutputDTO(PointScaleEntity pointScaleEntity) {
        return PointScaleOutputDTO.builder()
                .id(pointScaleEntity.getId())
                .whatTasted(pointScaleEntity.getWhatTasted())
                .whenTasted(pointScaleEntity.getWhenTasted())
                .whatSaw(pointScaleEntity.getWhatSaw())
                .whatAromas(pointScaleEntity.getWhatAromas())
                .whatFlavors(pointScaleEntity.getWhatFlavors())
                .whatOpinion(pointScaleEntity.getWhatOpinion())
                .pointScale(pointScaleEntity.getPointScale())
                .build();
    }

    public List<PointScaleOutputDTO> toOutputDTOList(List<PointScaleEntity> pointScaleEntityList) {
        return pointScaleEntityList.stream()
                .map(this::toOutputDTO)
                .toList();
    }

    public PointScaleOutputDTO toOutputDTOUpdate(PointScaleEntity pointScaleEntity, UUID uuid, PointScaleOutputDTO pointScaleOutputDTO) {
        return PointScaleOutputDTO.builder()
                .id(uuid)
                .whatTasted(pointScaleEntity.getWhatTasted() != null ? pointScaleEntity.getWhatTasted() : pointScaleOutputDTO.getWhatTasted())
                .whenTasted(pointScaleEntity.getWhenTasted() != null ? pointScaleEntity.getWhenTasted() : pointScaleOutputDTO.getWhenTasted())
                .whatSaw(pointScaleEntity.getWhatSaw() != null ? pointScaleEntity.getWhatSaw() : pointScaleOutputDTO.getWhatSaw())
                .whatAromas(pointScaleEntity.getWhatAromas() != null ? pointScaleEntity.getWhatAromas() : pointScaleOutputDTO.getWhatAromas())
                .whatFlavors(pointScaleEntity.getWhatFlavors() != null ? pointScaleEntity.getWhatFlavors() : pointScaleOutputDTO.getWhatFlavors())
                .whatOpinion(pointScaleEntity.getWhatOpinion() != null ? pointScaleEntity.getWhatOpinion() : pointScaleOutputDTO.getWhatOpinion())
                .pointScale(pointScaleEntity.getPointScale() != null ? pointScaleEntity.getPointScale() : pointScaleOutputDTO.getPointScale())
                .build();
    }
}
