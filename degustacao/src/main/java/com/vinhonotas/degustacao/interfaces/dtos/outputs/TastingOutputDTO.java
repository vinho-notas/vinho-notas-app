package com.vinhonotas.degustacao.interfaces.dtos.outputs;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.EnumTastingType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class TastingOutputDTO {

    private UUID id;
    private LocalDate tastingData;
    private EnumTastingType tastingType;
    private Set<TastingCardEntity> tastingCards;

}
