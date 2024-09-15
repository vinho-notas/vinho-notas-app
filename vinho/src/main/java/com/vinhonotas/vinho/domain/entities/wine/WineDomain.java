package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WineDomain {

    private String sku;
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

    public WineDomain(final String sku, final String name, final BigDecimal price, final String purchaseLocation,
                      final LocalDate purchaseDate, final EnumWineType wineType, final EnumWineClassification wineClassification,
                      final String alcoholContent, final int volumeMl, final String grape, final String winery, final String serviceTemperature,
                      final String harvest, final String country, final String guardTime, final String region, final String maturation,
                      final String harmonization) {
        this.sku = sku;
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
    }

    public static WineDomainBuilder builder() {
        return new WineDomainBuilder();
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPurchaseLocation() {
        return purchaseLocation;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public EnumWineType getWineType() {
        return wineType;
    }

    public EnumWineClassification getWineClassification() {
        return wineClassification;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public String getGrape() {
        return grape;
    }

    public String getWinery() {
        return winery;
    }

    public String getServiceTemperature() {
        return serviceTemperature;
    }

    public String getHarvest() {
        return harvest;
    }

    public String getCountry() {
        return country;
    }

    public String getGuardTime() {
        return guardTime;
    }

    public String getRegion() {
        return region;
    }

    public String getMaturation() {
        return maturation;
    }

    public String getHarmonization() {
        return harmonization;
    }

    @Override
    public String toString() {
        return "WineDomain{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", purchaseLocation='" + purchaseLocation + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", wineType=" + wineType +
                ", wineClassification=" + wineClassification +
                ", alcoholContent='" + alcoholContent + '\'' +
                ", volumeMl=" + volumeMl +
                ", grape='" + grape + '\'' +
                ", winery='" + winery + '\'' +
                ", serviceTemperature='" + serviceTemperature + '\'' +
                ", harvest='" + harvest + '\'' +
                ", country='" + country + '\'' +
                ", guardTime='" + guardTime + '\'' +
                ", region='" + region + '\'' +
                ", maturation='" + maturation + '\'' +
                ", harmonization='" + harmonization + '\'' +
                '}';
    }

    public static class WineDomainBuilder {
        private String sku;
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

        WineDomainBuilder() {
        }

        /**
         * Construtor do SKU - Stock Keeping Unit, ou Unidade de Manutenção de Estoque. É um código exclusivo atribuído ao vinho
         * para facilitar a sua identificação e gestão de estoque. Cada SKU é específico para um vinho e pode diferenciar produtos
         * com base em:
         * - 2 primeiras letras do name
         * - 2 primeiras letras do EnumWineType
         * - 2 primeiras letras do EnumWineClassification
         * - harvest
         * - 2 primeiras letras do country.
         * @param name
         * @param wineType
         * @param wineClassification
         * @param harvest
         * @param country
         * @return String sku
         */
        private String generateSku(String name, EnumWineType wineType, EnumWineClassification wineClassification, String harvest, String country) {
            return name.substring(0, 2) + wineType.name().substring(0, 2) + wineClassification.name().substring(0, 2) + harvest + country.substring(0, 2);
        }

        public WineDomainBuilder sku(String name, EnumWineType wineType, EnumWineClassification wineClassification, String harvest, String country) {
            this.sku = generateSku(name, wineType, wineClassification, harvest, country);
            return this;
        }

        public WineDomainBuilder name(String name) {
            this.name = name;
            return this;
        }

        public WineDomainBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public WineDomainBuilder purchaseLocation(String purchaseLocation) {
            this.purchaseLocation = purchaseLocation;
            return this;
        }

        public WineDomainBuilder purchaseDate(LocalDate purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public WineDomainBuilder wineType(EnumWineType wineType) {
            this.wineType = wineType;
            return this;
        }

        public WineDomainBuilder wineClassification(EnumWineClassification wineClassification) {
            this.wineClassification = wineClassification;
            return this;
        }

        public WineDomainBuilder alcoholContent(String alcoholContent) {
            this.alcoholContent = alcoholContent;
            return this;
        }

        public WineDomainBuilder volumeMl(int volumeMl) {
            this.volumeMl = volumeMl;
            return this;
        }

        public WineDomainBuilder grape(String grape) {
            this.grape = grape;
            return this;
        }

        public WineDomainBuilder winery(String winery) {
            this.winery = winery;
            return this;
        }

        public WineDomainBuilder serviceTemperature(String serviceTemperature) {
            this.serviceTemperature = serviceTemperature;
            return this;
        }

        public WineDomainBuilder harvest(String harvest) {
            this.harvest = harvest;
            return this;
        }

        public WineDomainBuilder country(String country) {
            this.country = country;
            return this;
        }

        public WineDomainBuilder guardTime(String guardTime) {
            this.guardTime = guardTime;
            return this;
        }

        public WineDomainBuilder region(String region) {
            this.region = region;
            return this;
        }

        public WineDomainBuilder maturation(String maturation) {
            this.maturation = maturation;
            return this;
        }

        public WineDomainBuilder harmonization(String harmonization) {
            this.harmonization = harmonization;
            return this;
        }

        public WineDomain build() {
            if (this.sku == null) {
                this.sku = generateSku(this.name, this.wineType, this.wineClassification, this.harvest, this.country);
            }

            return new WineDomain(this.sku, this.name, this.price, this.purchaseLocation, this.purchaseDate, this.wineType, this.wineClassification, this.alcoholContent, this.volumeMl, this.grape, this.winery, this.serviceTemperature, this.harvest, this.country, this.guardTime, this.region, this.maturation, this.harmonization);
        }

    }
}
