package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class WineEntity {

    private String name;
    private BigDecimal price;
    private String purchaseLocation;
    private LocalDate purchaseDate;
    private EnumWineType wineType;
    private EnumWineClassification wineClassification;
    private String alcoholContent;
    private int volumeMl;
    private String grape;
    private String winery;
    private String serviceTemperature;
    private String harvest;
    private String country;
    private String guardTime;
    private String region;
    private String maturation;
    private String harmonization;
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;
    private Long version;

    public WineEntity(String name, BigDecimal price, String purchaseLocation, LocalDate purchaseDate, EnumWineType wineType,
                      EnumWineClassification wineClassification, String alcoholContent, int volumeMl, String grape,
                      String winery, String serviceTemperature, String harvest, String country, String guardTime, String region,
                      String maturation, String harmonization, LocalDateTime dthreg, String userreg, LocalDateTime dthalt,
                      String useralt, Long version) {
        if (!validatePrice(price)) {
            throw new IllegalArgumentException("Preço inválido");
        }
        this.name = name;
        this.price = price;
        this.purchaseLocation = purchaseLocation;
        this.purchaseDate = purchaseDate;
        this.wineType = wineType;
        this.wineClassification = wineClassification;
        this.alcoholContent = alcoholContent;
        this.volumeMl = volumeMl;
        this.grape = grape;
        this.winery = winery;
        this.serviceTemperature = serviceTemperature;
        this.harvest = harvest;
        this.country = country;
        this.guardTime = guardTime;
        this.region = region;
        this.maturation = maturation;
        this.harmonization = harmonization;
        this.dthreg = dthreg;
        this.userreg = userreg;
        this.dthalt = dthalt;
        this.useralt = useralt;
        this.version = version;
    }

    private boolean validatePrice(Object value) {
        try {
            BigDecimal price;

            if (value instanceof String) {
                price = new BigDecimal((String) value);
            } else if (value instanceof BigDecimal) {
                price = (BigDecimal) value;
            } else {
                return false;
            }

            return price.compareTo(BigDecimal.ZERO) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
