package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

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

}
