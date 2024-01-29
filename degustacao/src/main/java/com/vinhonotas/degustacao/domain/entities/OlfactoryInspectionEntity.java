package com.vinhonotas.degustacao.domain.entities;

import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumIntensityType;
import com.vinhonotas.degustacao.domain.enums.EnumPersistenceType;
import com.vinhonotas.degustacao.domain.enums.EnumQualityType;
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
@Table(name = "Tbolfactoryinspection", schema = "degustacao")
public class OlfactoryInspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "tastingdata")
    private LocalDate tastingData;
    @Column(name = "winetasted")
    private String wineTasted;
    @Column(name = "intensity")
    @Enumerated(EnumType.STRING)
    private EnumIntensityType intensity;
    @Column(name = "persistence")
    @Enumerated(EnumType.STRING)
    private EnumPersistenceType persistence;
    @Column(name = "quality")
    @Enumerated(EnumType.STRING)
    private EnumQualityType quality;
    @OneToOne
    @JoinColumn(name = "aromas_id", nullable = false)
    @Column(name = "aromas")
    private AromasEntity aromas;
    @Column(name = "classification")
    @Enumerated(EnumType.STRING)
    private EnumClassificationType classification;
    @Column(name = "tastingcard")
    @OneToOne(mappedBy = "olfactoryInspection")
    private TastingCardEntity tastingCard;

}
