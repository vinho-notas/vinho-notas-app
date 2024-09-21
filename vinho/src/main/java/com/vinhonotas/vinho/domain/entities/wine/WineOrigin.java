package com.vinhonotas.vinho.domain.entities.wine;

/**
 * Represents the origin of a wine.
 */
public class WineOrigin {

    private final String country;
    private final String region;
    private final String harvest;
    private final String guardTime;
    private final String maturation;
    private final String harmonization;

    public WineOrigin(String country, String region, String harvest, String guardTime, String maturation, String harmonization) {
        this.country = country;
        this.region = region;
        this.harvest = harvest;
        this.guardTime = guardTime;
        this.maturation = maturation;
        this.harmonization = harmonization;
    }

    public static WineOriginBuilder builder() {
        return new WineOriginBuilder();
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getHarvest() {
        return harvest;
    }

    public String getGuardTime() {
        return guardTime;
    }

    public String getMaturation() {
        return maturation;
    }

    public String getHarmonization() {
        return harmonization;
    }

    @Override
    public String toString() {
        return "WineOrigin{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", harvest='" + harvest + '\'' +
                ", guardTime='" + guardTime + '\'' +
                ", maturation='" + maturation + '\'' +
                ", harmonization='" + harmonization + '\'' +
                '}';
    }

    public static class WineOriginBuilder {
        private String country;
        private String region;
        private String harvest;
        private String guardTime;
        private String maturation;
        private String harmonization;

        public WineOriginBuilder country(String country) {
            this.country = country;
            return this;
        }

        public WineOriginBuilder region(String region) {
            this.region = region;
            return this;
        }

        public WineOriginBuilder harvest(String harvest) {
            this.harvest = harvest;
            return this;
        }

        public WineOriginBuilder guardTime(String guardTime) {
            this.guardTime = guardTime;
            return this;
        }

        public WineOriginBuilder maturation(String maturation) {
            this.maturation = maturation;
            return this;
        }

        public WineOriginBuilder harmonization(String harmonization) {
            this.harmonization = harmonization;
            return this;
        }

        public WineOrigin build() {
            return new WineOrigin(country, region, harvest, guardTime, maturation, harmonization);
        }
    }

}
