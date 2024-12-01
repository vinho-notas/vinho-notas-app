package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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

        Assertions.assertNotNull(wineDomain1);
        Assertions.assertNotNull(wineDomain2);
        Assertions.assertNotNull(wineDomain3);
        Assertions.assertNotNull(wineDomain4);

        assertEquals("Luca Bosio Vineyards Barolo DOCG 2020", wineDomain1.getName());
        assertEquals("Luca Bosio Vineyards Barolo DOCG 2020", wineDomain2.getName());
        assertEquals("Expedicion Single Vineyard Selection Sauvignon Blanc Central Valley D.O. 2023", wineDomain3.getName());
        assertEquals("Expedicion Single Vineyard Selection Sauvignon Blanc Central Valley D.O. 2023", wineDomain4.getName());

        assertNotNull(wineDomain1.getWineDetails());
        assertNotNull(wineDomain2.getWineDetails());
        assertNotNull(wineDomain3.getWineDetails());
        assertNotNull(wineDomain4.getWineDetails());

        assertNotNull(wineDomain1.getPurchaseInfo());
        assertNotNull(wineDomain2.getPurchaseInfo());
        assertNotNull(wineDomain3.getPurchaseInfo());
        assertNotNull(wineDomain4.getPurchaseInfo());

        assertNotNull(wineDomain1.getWineOrigin());
        assertNotNull(wineDomain2.getWineOrigin());
        assertNotNull(wineDomain3.getWineOrigin());
        assertNotNull(wineDomain4.getWineOrigin());

        assertEquals("LuREDR2020It", wineDomain1.getSku());
        assertEquals("LuWHDE2023It", wineDomain2.getSku());
        assertEquals("ExBLIC2010It", wineDomain3.getSku());
        assertEquals("ExNASW2019It", wineDomain4.getSku());
    }

}