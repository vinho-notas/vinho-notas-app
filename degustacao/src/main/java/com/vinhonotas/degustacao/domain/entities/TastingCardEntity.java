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
@Table(name = "Tbtastingcard", schema = "degustacao")
public class TastingCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "winetasted")
    private String wineTasted;

    @Column(name = "tastingdata")
    private LocalDate tastingData;

    @Column(name = "harvest")
    private String harvest;

    @Column(name = "grapes")
    private String grapes;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "clarity")
    @Enumerated(EnumType.STRING)
    private EnumClarityType clarity;

    @Column(name = "brightness")
    @Enumerated(EnumType.STRING)
    private EnumBrightnessType brightness;

    @Column(name = "viscosity")
    @Enumerated(EnumType.STRING)
    private EnumViscosityType viscosity;

    @Column(name = "colorred")
    @Enumerated(EnumType.STRING)
    private EnumRedColorType colorRed;

    @Column(name = "colorwhite")
    @Enumerated(EnumType.STRING)
    private EnumWhiteColorType colorWhite;

    @Column(name = "colorrose")
    @Enumerated(EnumType.STRING)
    private EnumRoseColorType colorRose;

    @Column(name = "visual_inspection_classification")
    @Enumerated(EnumType.STRING)
    private EnumClassificationType visualInspectionClassification;

    @Column(name = "intensity")
    @Enumerated(EnumType.STRING)
    private EnumIntensityType intensity;

    @Column(name = "olfactory_inspection_persistence")
    @Enumerated(EnumType.STRING)
    private EnumPersistenceType olfactoryInspectionPersistence;

    @Column(name = "quality")
    @Enumerated(EnumType.STRING)
    private EnumQualityType quality;

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

    @Column(name = "olfactory_inspection_classification")
    @Enumerated(EnumType.STRING)
    private EnumClassificationType olfactoryInspectionClassification;

    @Column(name = "body")
    @Enumerated(EnumType.STRING)
    private EnumBodyType body;

    @Column(name = "sweetness")
    @Enumerated(EnumType.STRING)
    private EnumSweetnessType sweetness;

    @Column(name = "tannin")
    @Enumerated(EnumType.STRING)
    private EnumTanninType tannin;

    @Column(name = "acidity")
    @Enumerated(EnumType.STRING)
    private EnumAcidityType acidity;

    @Column(name = "alcohol")
    @Enumerated(EnumType.STRING)
    private EnumAlcoholType alcohol;

    @Column(name = "gustatory_inspection_persistence")
    @Enumerated(EnumType.STRING)
    private EnumPersistenceType gustatoryInspectionPersistence;

    @Column(name = "maturity")
    @Enumerated(EnumType.STRING)
    private EnumMaturityType maturity;

    @Column(name = "typicality")
    @Enumerated(EnumType.STRING)
    private EnumTypicalityType typicality;

    @Column(name = "gustatory_inspection_classification")
    @Enumerated(EnumType.STRING)
    private EnumClassificationType gustatoryInspectionClassification;

    @Column(name = "opinion")
    private String opinion;

    @Column(name = "pointscale")
    @Enumerated(EnumType.STRING)
    private EnumPointScale pointScale;

    @Column(name = "dthreg")
    private LocalDateTime dthreg;

    @Column(name = "userreg")
    private String userreg;

    @Column(name = "dthalt")
    private LocalDateTime dthalt;

    @Column(name = "useralt")
    private String useralt;

}
