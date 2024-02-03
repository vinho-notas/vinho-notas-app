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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "visualinspection_id", referencedColumnName = "id")
    private VisualInspectionEntity visualInspection;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "olfactoryinspection_id", referencedColumnName = "id")
    private OlfactoryInspectionEntity olfactoryInspection;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gustatoryinspection_id", referencedColumnName = "id")
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
