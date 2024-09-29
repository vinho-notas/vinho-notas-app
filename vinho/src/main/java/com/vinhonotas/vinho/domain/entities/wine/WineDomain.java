package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;

public class WineDomain {

    private final String sku;
    private final String name;
    private final WineDetails wineDetails;
    private final PurchaseInfo purchaseInfo;
    private final WineOrigin wineOrigin;

    public WineDomain(final String sku, final String name, final WineDetails wineDetails, final PurchaseInfo purchaseInfo,
                      final WineOrigin wineOrigin) {
        this.sku = sku;
        this.name = name;
        this.wineDetails = wineDetails;
        this.purchaseInfo = purchaseInfo;
        this.wineOrigin = wineOrigin;
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

    public WineDetails getWineDetails() {
        return wineDetails;
    }

    public PurchaseInfo getPurchaseInfo() {
        return purchaseInfo;
    }

    public WineOrigin getWineOrigin() {
        return wineOrigin;
    }

    @Override
    public String toString() {
        return "WineDomain{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", wineDetails=" + wineDetails +
                ", purchaseInfo=" + purchaseInfo +
                ", wineOrigin=" + wineOrigin +
                '}';
    }

    public static class WineDomainBuilder {
        private String sku;
        private String name;
        private WineDetails wineDetails;
        private PurchaseInfo purchaseInfo;
        private WineOrigin wineOrigin;

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
         * @param name: nome do vinho
         * @param wineType: tipo do vinho
         * @param wineClassification: classificação do vinho
         * @param harvest: safra do vinho
         * @param country: país de origem do vinho
         * @return String sku: código SKU gerado
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

        public WineDomainBuilder wineDetails(WineDetails wineDetails) {
            this.wineDetails = wineDetails;
            return this;
        }

        public WineDomainBuilder purchaseInfo(PurchaseInfo purchaseInfo) {
            this.purchaseInfo = purchaseInfo;
            return this;
        }

        public WineDomainBuilder wineOrigin(WineOrigin wineOrigin) {
            this.wineOrigin = wineOrigin;
            return this;
        }

        public WineDomain build() {
            if (this.sku == null) {
                this.sku = generateSku(this.name, this.wineDetails.getWineType(), this.wineDetails.getWineClassification(),
                        this.wineOrigin.getHarvest(), this.wineOrigin.getCountry());
            }

            return new WineDomain(this.sku, this.name, this.wineDetails, this.purchaseInfo, this.wineOrigin);
        }

    }

}
