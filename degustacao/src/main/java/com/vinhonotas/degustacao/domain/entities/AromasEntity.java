package com.vinhonotas.degustacao.domain.entities;

import com.vinhonotas.degustacao.domain.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tbaromas", schema = "degustacao")
public class AromasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "tastingdata")
    private LocalDate tastingData;

    @Column(name = "winetasted")
    private String wineTasted;

    @Column(name = "fruity")
    @Enumerated(EnumType.STRING)
    private EnumFruityType fruity;

    @Column(name = "dryfruits")
    @Enumerated(EnumType.STRING)
    private EnumDryFruitsType dryFruits;

    @Column(name = "florals")
    @Enumerated(EnumType.STRING)
    private EnumFloralsType florals;

    @Column(name = "vegetablesandherbs")
    @Enumerated(EnumType.STRING)
    private EnumVegetablesAndHerbsType vegetablesAndHerbs;

    @Column(name = "minerals")
    @Enumerated(EnumType.STRING)
    private EnumMineralsType minerals;

    @Column(name = "spices")
    @Enumerated(EnumType.STRING)
    private EnumSpicesType spices;

    @Column(name = "animals")
    @Enumerated(EnumType.STRING)
    private EnumAnimalsType animals;

    @Column(name = "empireumatics")
    @Enumerated(EnumType.STRING)
    private EnumEmpireumaticsType empireumatics;

    @Column(name = "wood")
    @Enumerated(EnumType.STRING)
    private EnumWoodType wood;

    @Column(name = "chemicals")
    @Enumerated(EnumType.STRING)
    private EnumChemicalsAndEtherealType chemicals;

    @Column(name = "lacteal")
    @Enumerated(EnumType.STRING)
    private EnumLactealType lacteal;

    @Column(name = "sweets")
    @Enumerated(EnumType.STRING)
    private EnumSweetsType sweets;

    @Column(name = "classification")
    @Enumerated(EnumType.STRING)
    private EnumClassificationType classification;

}
