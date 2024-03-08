package com.vinhonotas.bff.interfaces.dtos.outputs.degustacao;

import com.vinhonotas.bff.domain.enums.EnumTastingType;
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
    private Set<TastingCardOutputDTO> tastingCards;

}
