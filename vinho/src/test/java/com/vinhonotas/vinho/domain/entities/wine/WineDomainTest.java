package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WineDomainTest {

    @Test
    void testCreateWine() {
        WineDomain wineDomain1 = assertDoesNotThrow(() -> WineDomain.builder()
                .name("Luca Bosio Vineyards Barolo DOCG 2020")
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .harvest("2020")
                .country("Italia")
                .build());

        WineDomain wineDomain2 = assertDoesNotThrow(() -> WineDomain.builder()
                .name("Luca Bosio Vineyards Barolo DOCG 2020")
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .harvest("2021")
                .country("Italia")
                .build());

        WineDomain wineDomain3 = assertDoesNotThrow(() -> WineDomain.builder()
                .name("Expedicion Single Vineyard Selection Sauvignon Blanc Central Valley D.O. 2023")
                .wineType(EnumWineType.WHITEWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .harvest("2023")
                .country("Chile")
                .build());

        WineDomain wineDomain4 = assertDoesNotThrow(() -> WineDomain.builder()
                .name("Expedicion Single Vineyard Selection Sauvignon Blanc Central Valley D.O. 2023")
                .wineType(EnumWineType.WHITEWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .harvest("2024")
                .country("Chile")
                .build());

        log.info("WineDomain1: SKU: {}", wineDomain1.getSku());
        log.info("WineDomain2: SKU: {}", wineDomain2.getSku());
        log.info("WineDomain3: SKU: {}", wineDomain3.getSku());
        log.info("WineDomain4: SKU: {}", wineDomain4.getSku());


    }

}