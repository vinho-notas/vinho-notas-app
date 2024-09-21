package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
class WineDomainTest {

    @Test
    void testCreateWine() {
        WineDomain wineDomain1 = assertDoesNotThrow(() -> WineDomain.builder()
                .name("Luca Bosio Vineyards Barolo DOCG 2020")
                .wineDetails(
                        WineDetails.builder()
                                .wineType(EnumWineType.REDWINE)
                                .wineClassification(EnumWineClassification.DRYWINE)
                                .alcoholContent("14.5%")
                                .volumeMl(750)
                                .grape("Nebbiolo")
                                .winery("Luca Bosio Vineyards")
                                .serviceTemperature("16-18°C")
                                .build()
                )
                .purchaseInfo(PurchaseInfo.builder()
                        .price(BigDecimal.valueOf(150.00))
                        .purchaseLocation("Vinhonotas")
                        .purchaseDate(LocalDate.now())
                        .build()
                )
                .wineOrigin(WineOrigin.builder()
                        .country("Italia")
                        .region("Piemonte")
                        .harvest("2020")
                        .guardTime("10 anos")
                        .maturation("24 meses")
                        .harmonization("Carnes vermelhas grelhadas")
                        .build()
                )
                .build());

        WineDomain wineDomain2 = assertDoesNotThrow(() -> WineDomain.builder()
                .name("Luca Bosio Vineyards Barolo DOCG 2020")
                .wineDetails(
                        WineDetails.builder()
                                .wineType(EnumWineType.WHITEWINE)
                                .wineClassification(EnumWineClassification.DEMISECWINE)
                                .alcoholContent("14.5%")
                                .volumeMl(750)
                                .grape("Nebbiolo")
                                .winery("Luca Bosio Vineyards")
                                .serviceTemperature("16-18°C")
                                .build()

                )
                .purchaseInfo(PurchaseInfo.builder()
                        .price(BigDecimal.valueOf(200.00))
                        .purchaseLocation("Vinhonotas")
                        .purchaseDate(LocalDate.now())
                        .build()
                )
                .wineOrigin(WineOrigin.builder()
                        .country("Italia")
                        .region("Piemonte")
                        .harvest("2023")
                        .guardTime("10 anos")
                        .maturation("24 meses")
                        .harmonization("Carnes vermelhas grelhadas")
                        .build()
                )
                .build());

        WineDomain wineDomain3 = assertDoesNotThrow(() -> WineDomain.builder()
                .name("Expedicion Single Vineyard Selection Sauvignon Blanc Central Valley D.O. 2023")
                .wineDetails(WineDetails.builder()
                        .wineType(EnumWineType.BLENDEDWINE)
                        .wineClassification(EnumWineClassification.ICEWINE)
                        .alcoholContent("14.5%")
                        .volumeMl(750)
                        .grape("Nebbiolo")
                        .winery("Luca Bosio Vineyards")
                        .serviceTemperature("16-18°C")
                        .build()
                )
                .purchaseInfo(PurchaseInfo.builder()
                        .price(BigDecimal.valueOf(145.00))
                        .purchaseLocation("Vinhonotas")
                        .purchaseDate(LocalDate.now())
                        .build()
                )
                .wineOrigin(WineOrigin.builder()
                        .country("Italia")
                        .region("Piemonte")
                        .harvest("2010")
                        .guardTime("10 anos")
                        .maturation("24 meses")
                        .harmonization("Carnes vermelhas grelhadas")
                        .build()
                )
                .build());

        WineDomain wineDomain4 = assertDoesNotThrow(() -> WineDomain.builder()
                .name("Expedicion Single Vineyard Selection Sauvignon Blanc Central Valley D.O. 2023")
                .wineDetails(WineDetails.builder()
                        .wineType(EnumWineType.NATURALWINE)
                        .wineClassification(EnumWineClassification.SWEETWINE)
                        .alcoholContent("14.5%")
                        .volumeMl(750)
                        .grape("Nebbiolo")
                        .winery("Luca Bosio Vineyards")
                        .serviceTemperature("16-18°C")
                        .build()
                )
                .purchaseInfo(PurchaseInfo.builder()
                        .price(BigDecimal.valueOf(100.00))
                        .purchaseLocation("Vinhonotas")
                        .purchaseDate(LocalDate.now())
                        .build()
                )
                .wineOrigin(WineOrigin.builder()
                        .country("Italia")
                        .region("Piemonte")
                        .harvest("2019")
                        .guardTime("10 anos")
                        .maturation("24 meses")
                        .harmonization("Carnes vermelhas grelhadas")
                        .build())
                .build());

        log.info("WineDomain1: SKU: {}", wineDomain1.getSku());
        log.info("WineDomain2: SKU: {}", wineDomain2.getSku());
        log.info("WineDomain3: SKU: {}", wineDomain3.getSku());
        log.info("WineDomain4: SKU: {}", wineDomain4.getSku());

    }

}