package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;

/**
 * Represents the details of a wine.
 */
public class WineDetails {

    private final EnumWineType wineType;
    private final EnumWineClassification wineClassification;
    private final String alcoholContent;
    private final Integer volumeMl;
    private final String grape;
    private final String winery;
    private final String serviceTemperature;

    public WineDetails(EnumWineType wineType, EnumWineClassification wineClassification, String alcoholContent,
                       Integer volumeMl, String grape, String winery, String serviceTemperature) {
        this.wineType = wineType;
        this.wineClassification = wineClassification;
        this.alcoholContent = alcoholContent;
        this.volumeMl = volumeMl;
        this.grape = grape;
        this.winery = winery;
        this.serviceTemperature = serviceTemperature;
    }

    public static WineDetailsBuilder builder() {
        return new WineDetailsBuilder();
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

    public Integer getVolumeMl() {
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

    @Override
    public String toString() {
        return "WineDetails{" +
                "wineType=" + wineType +
                ", wineClassification=" + wineClassification +
                ", alcoholContent='" + alcoholContent + '\'' +
                ", volumeMl=" + volumeMl +
                ", grape='" + grape + '\'' +
                ", winery='" + winery + '\'' +
                ", serviceTemperature='" + serviceTemperature + '\'' +
                '}';
    }

    public static class WineDetailsBuilder{
        private EnumWineType wineType;
        private EnumWineClassification wineClassification;
        private String alcoholContent;
        private Integer volumeMl;
        private String grape;
        private String winery;
        private String serviceTemperature;

        WineDetailsBuilder() {
        }

        public WineDetailsBuilder wineType(EnumWineType wineType) {
            this.wineType = wineType;
            return this;
        }

        public WineDetailsBuilder wineClassification(EnumWineClassification wineClassification) {
            this.wineClassification = wineClassification;
            return this;
        }

        public WineDetailsBuilder alcoholContent(String alcoholContent) {
            this.alcoholContent = alcoholContent;
            return this;
        }

        public WineDetailsBuilder volumeMl(Integer volumeMl) {
            this.volumeMl = volumeMl;
            return this;
        }

        public WineDetailsBuilder grape(String grape) {
            this.grape = grape;
            return this;
        }

        public WineDetailsBuilder winery(String winery) {
            this.winery = winery;
            return this;
        }

        public WineDetailsBuilder serviceTemperature(String serviceTemperature) {
            this.serviceTemperature = serviceTemperature;
            return this;
        }

        public WineDetails build() {
            return new WineDetails(wineType, wineClassification, alcoholContent, volumeMl, grape, winery, serviceTemperature);
        }

    }

}
