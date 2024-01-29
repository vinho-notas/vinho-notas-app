package com.vinhonotas.degustacao.domain.entities;

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
    @Column(name = "hearvest")
    private int hearvest;
    @Column(name = "grapes")
    private String grapes;
    @Column(name = "country")
    private String country;
    @Column(name = "region")
    private String region;
    @Column(name = "visualinspection")
    private String visualInspection;
    @Column(name = "olfactoryinspection")
    private String olfactoryInspection;
    @Column(name = "gustatoryinspection")
    private String gustatoryInspection;
    @Column(name = "opinion")
    private String opinion;
    @Column(name = "score")
    private String score;
    @Column(name = "tasting")
    @ManyToOne
    @JoinColumn(name = "tasting_id", insertable = false, updatable = false)
    private TastingEntity tasting;

}
