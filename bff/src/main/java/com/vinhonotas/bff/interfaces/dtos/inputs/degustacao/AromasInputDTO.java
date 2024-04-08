package com.vinhonotas.bff.interfaces.dtos.inputs.degustacao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AromasInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private String fruity;
    private String dryFruits;
    private String florals;
    private String vegetablesAndHerbs;
    private String minerals;
    private String spices;
    private String animals;
    private String empireumatics;
    private String wood;
    private String chemicals;
    private String lacteal;
    private String sweets;
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
