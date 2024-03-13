package com.vinhonotas.bff.interfaces.dtos.outputs.degustacao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class AromasOutputDTO {

    private UUID id;
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
