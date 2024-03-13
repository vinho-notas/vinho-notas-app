package com.vinhonotas.degustacao.interfaces.dtos.outputs;

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
    private String tastingType;
    private Set<TastingCardOutputDTO> tastingCards;

}
