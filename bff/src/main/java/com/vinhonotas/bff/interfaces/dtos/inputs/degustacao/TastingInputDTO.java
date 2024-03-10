package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import com.vinhonotas.bff.domain.enums.EnumTastingType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class TastingInputDTO {

    private LocalDate tastingData;
    private EnumTastingType tastingType;
    private Set<TastingCardInputDTO> tastingCards;

}
