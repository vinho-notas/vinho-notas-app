package com.vinhonotas.vinho.domain.entities.wine;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents the purchase information of a wine.
 */
public class PurchaseInfo {

    private final BigDecimal price;
    private final String purchaseLocation;
    private final LocalDate purchaseDate;

    public PurchaseInfo(BigDecimal price, String purchaseLocation, LocalDate purchaseDate) {
        this.price = price;
        this.purchaseLocation = purchaseLocation;
        this.purchaseDate = purchaseDate;
    }

    public static PurchaseInfoBuilder builder() {
        return new PurchaseInfoBuilder();
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

    @Override
    public String toString() {
        return "PurchaseInfo{" +
                "price=" + price +
                ", purchaseLocation='" + purchaseLocation + '\'' +
                ", purchaseDate=" + purchaseDate +
                '}';
    }

    public static class PurchaseInfoBuilder {
        private BigDecimal price;
        private String purchaseLocation;
        private LocalDate purchaseDate;

        public PurchaseInfoBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public PurchaseInfoBuilder purchaseLocation(String purchaseLocation) {
            this.purchaseLocation = purchaseLocation;
            return this;
        }

        public PurchaseInfoBuilder purchaseDate(LocalDate purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public PurchaseInfo build() {
            return new PurchaseInfo(price, purchaseLocation, purchaseDate);
        }
    }
}
