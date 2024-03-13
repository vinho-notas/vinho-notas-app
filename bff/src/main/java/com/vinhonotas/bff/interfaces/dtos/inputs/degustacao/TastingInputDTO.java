package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class TastingInputDTO {

    private LocalDate tastingData;
    private String tastingType;
    private Set<TastingCardInputDTO> tastingCards;
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
