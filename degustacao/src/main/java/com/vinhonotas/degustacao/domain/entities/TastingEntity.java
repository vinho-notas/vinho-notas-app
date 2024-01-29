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
@Table(name = "Tbtasting", schema = "degustacao")
public class TastingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "tastingdata")
    private LocalDate tastingData;
    @Column(name = "winetasted")
    private String wineTasted;
    @Column(name = "hearvest")
    private String hearvest;
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

}
