package com.vinhonotas.vinho.domain.entities.wine;

import com.vinhonotas.vinho.v1.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.v1.domain.enums.EnumWineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WineEntityTest {

    @Test
    @DisplayName("Deve criar um vinho com sucesso")
    void testCreateWine() {

        assertDoesNotThrow(() -> {
            WineEntity wine = WineEntity.builder()
                    .name("Portada Winemaker's Selection 2021")
                    .price(BigDecimal.valueOf(69.90))
                    .purchaseLocation("Evino")
                    .purchaseDate(LocalDate.now())
                    .wineType(EnumWineType.REDWINE)
                    .wineClassification(EnumWineClassification.DRYWINE)
                    .alcoholContent("12.5")
                    .volumeMl(750)
                    .grape("Uvas variadas")
                    .winery("DFJ Vinhos")
                    .serviceTemperature("17")
                    .harvest("2021")
                    .country("Portugal")
                    .guardTime("2025")
                    .region("Lisboa")
                    .maturation("1 mês em garrafa")
                    .harmonization("Carnes vermelhas grelhadas")
                    .dthreg(LocalDateTime.now())
                    .userreg("admin")
                    .dthalt(LocalDateTime.now())
                    .useralt("admin")
                    .version(1L)
                    .build();
        });

    }

    @Test
    void testCreateWineWithPriceInvalid() {

            assertThrows(IllegalArgumentException.class, () -> {
                WineEntity wine = WineEntity.builder()
                        .name("Portada Winemaker's Selection 2021")
                        .price(BigDecimal.valueOf(-69.90))
                        .purchaseLocation("Evino")
                        .purchaseDate(LocalDate.now())
                        .wineType(EnumWineType.REDWINE)
                        .wineClassification(EnumWineClassification.DRYWINE)
                        .alcoholContent("12.5")
                        .volumeMl(750)
                        .grape("Uvas variadas")
                        .winery("DFJ Vinhos")
                        .serviceTemperature("17")
                        .harvest("2021")
                        .country("Portugal")
                        .guardTime("2025")
                        .region("Lisboa")
                        .maturation("1 mês em garrafa")
                        .harmonization("Carnes vermelhas grelhadas")
                        .dthreg(LocalDateTime.now())
                        .userreg("admin")
                        .dthalt(LocalDateTime.now())
                        .useralt("admin")
                        .version(1L)
                        .build();
            });
    }

}