package com.vinhonotas.degustacao.domain.entities;

import com.vinhonotas.degustacao.domain.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tbgustatoryinspection", schema = "degustacao")
public class GustatoryInspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "tastingdata")
    private LocalDate tastingData;

    @Column(name = "winetasted")
    private String wineTasted;

    @Column(name = "body")
    @Enumerated(EnumType.STRING)
    private EnumBodyType body;

    @Column(name = "sweetness")
    @Enumerated(EnumType.STRING)
    private EnumSweetnessType sweetness;

    @Column(name = "tannin")
    @Enumerated(EnumType.STRING)
    private EnumTanninType tannin;

    @Column(name = "classification")
    @Enumerated(EnumType.STRING)
    private EnumClassificationType classification;

    @Column(name = "acidity")
    @Enumerated(EnumType.STRING)
    private EnumAcidityType acidity;

    @Column(name = "alcohol")
    @Enumerated(EnumType.STRING)
    private EnumAlcoholType alcohol;

    @Column(name = "persistence")
    @Enumerated(EnumType.STRING)
    private EnumPersistenceType persistence;

    @Column(name = "maturity")
    @Enumerated(EnumType.STRING)
    private EnumMaturityType maturity;

    @Column(name = "typicality")
    @Enumerated(EnumType.STRING)
    private EnumTypicalityType typicality;

    @Column(name = "dthreg")
    private LocalDateTime dthreg;

    @Column(name = "userreg")
    private String userreg;

    @Column(name = "dthalt")
    private LocalDateTime dthalt;

    @Column(name = "useralt")
    private String useralt;

}
