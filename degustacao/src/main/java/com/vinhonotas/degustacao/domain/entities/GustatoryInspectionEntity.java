package com.vinhonotas.degustacao.domain.entities;

import com.vinhonotas.degustacao.domain.enums.EnumBodyType;
import com.vinhonotas.degustacao.domain.enums.EnumClassificationType;
import com.vinhonotas.degustacao.domain.enums.EnumSweetnessType;
import com.vinhonotas.degustacao.domain.enums.EnumTanninType;
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
    @Column(name = "tastingcard")
    @OneToOne(mappedBy = "gustatoryInspection")
    private TastingCardEntity tastingCard;

}
