package com.vinhonotas.vinho.domain.entities;

import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tbwine", schema = "vinho")
public class WineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "purchaselocation")
    private String purchaseLocation;
    @Column(name = "purchasedate")
    private LocalDate purchaseDate;
    @Column(name = "winetype")
    @Enumerated(EnumType.STRING)
    private EnumWineType wineType;
    @Column(name = "wineclassification")
    @Enumerated(EnumType.STRING)
    private EnumWineClassification wineClassification;
    @Column(name = "alcoholcontent")
    private double alcoholContent;
    @Column(name = "volume")
    private int volumeMl;
    @Column(name = "grape")
    private String grape;
    @Column(name = "winery")
    private String winery;
    @Column(name = "servicetemperature")
    private double serviceTemperature;
    @Column(name = "harvest")
    private int harvest;
    @Column(name = "country")
    private String country;
    @Column(name = "guardtime")
    private String guardTime;
    @Column(name = "region")
    private String region;
    @Column(name = "maturation")
    private String maturation;
    @Column(name = "harmonization")
    private String harmonization;
}
