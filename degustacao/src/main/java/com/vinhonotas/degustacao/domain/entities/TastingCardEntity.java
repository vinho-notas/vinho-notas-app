package com.vinhonotas.degustacao.domain.entities;

import com.vinhonotas.degustacao.domain.enums.EnumPointScale;
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
@Table(name = "Tbtastingcard", schema = "degustacao")
public class TastingCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "tastingdata")
    private LocalDate tastingData;

    @Column(name = "winetasted")
    private String wineTasted;

    @Column(name = "harvest")
    private String harvest;

    @Column(name = "grapes")
    private String grapes;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @OneToOne
    @JoinColumn(name = "visualinspection_id", nullable = false)
    private VisualInspectionEntity visualInspection;

    @OneToOne
    @JoinColumn(name = "olfactoryinspection_id", nullable = false)
    private OlfactoryInspectionEntity olfactoryInspection;

    @OneToOne
    @JoinColumn(name = "gustatoryinspection_id", nullable = false)
    private GustatoryInspectionEntity gustatoryInspection;

    @Column(name = "opinion")
    private String opinion;

    @Column(name = "pointscale")
    @Enumerated(EnumType.STRING)
    private EnumPointScale pointScale;

    @ManyToOne
    @JoinColumn(name = "tasting_id", insertable = false, updatable = false)
    private TastingEntity tasting;

}
