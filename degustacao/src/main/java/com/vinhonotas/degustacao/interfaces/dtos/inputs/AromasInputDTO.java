package com.vinhonotas.degustacao.interfaces.dtos.inputs;

import com.vinhonotas.degustacao.domain.enums.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AromasInputDTO {

    private LocalDate tastingData;
    private String wineTasted;
    private EnumFruityType fruity;
    private EnumDryFruitsType dryFruits;
    private EnumFloralsType florals;
    private EnumVegetablesAndHerbsType vegetablesAndHerbs;
    private EnumMineralsType minerals;
    private EnumSpicesType spices;
    private EnumAnimalsType animals;
    private EnumEmpireumaticsType empireumatics;
    private EnumWoodType wood;
    private EnumChemicalsAndEtherealType chemicals;
    private EnumLactealType lacteal;
    private EnumSweetsType sweets;

}
