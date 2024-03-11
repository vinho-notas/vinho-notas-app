package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class TastingInputDTO {

    private LocalDate tastingData;
    private String tastingType;
    private Set<TastingCardInputDTO> tastingCards;

}
