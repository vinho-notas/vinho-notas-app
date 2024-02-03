package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.enums.EnumTastingType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class TastingInputDTO {

    private LocalDate tastingData;
    private EnumTastingType tastingType;
    private Set<TastingCardEntity> tastingCards;

}
