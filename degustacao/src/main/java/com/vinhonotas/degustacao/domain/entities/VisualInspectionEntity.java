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
@Table(name = "Tbvisualinspection", schema = "degustacao")
public class VisualInspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "tastingdata")
    LocalDate tastingData;
    @Column(name = "winetasted")
    String wineTasted;
    @Column(name = "clarity")
    @Enumerated(EnumType.STRING)
    EnumClarityType clarity;
    @Column(name = "brightness")
    @Enumerated(EnumType.STRING)
    EnumBrightnessType brightness;
    @Column(name = "viscosity")
    @Enumerated(EnumType.STRING)
    EnumViscosityType viscosity;
    @Column(name = "colorred")
    @Enumerated(EnumType.STRING)
    EnumRedColorType colorRed;
    @Column(name = "colorwhite")
    @Enumerated(EnumType.STRING)
    EnumWhiteColorType colorWhite;
    @Column(name = "colorrose")
    @Enumerated(EnumType.STRING)
    EnumRoseColorType colorRose;
    @Column(name = "classification")
    @Enumerated(EnumType.STRING)
    EnumClassificationType classification;
}
