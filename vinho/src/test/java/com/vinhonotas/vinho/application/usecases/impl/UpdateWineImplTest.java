package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.UpdateWineRepository;
import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateWineImplTest {

    private static final String WINE_ID = "f7b3f1b3-4b3b-4b3b-4b3b-4b3b4b3b4b3b";

    @InjectMocks
    private UpdateWineImpl updateWineImpl;

    @Mock
    private UpdateWineRepository updateWineRepository;

    private WineDomain wineDomain;
    private WineEntity wineEntity;

    @BeforeEach
    void setUp() {
        wineDomain = createWineDomain();
        wineEntity = createWineEntity();
    }

    @Test
    @DisplayName("Deve atualizar um vinho pelo id")
    void testUpdateWine() {
        when(updateWineRepository.updateWine(WINE_ID, wineDomain)).thenReturn(wineEntity);

        WineEntity updatedWine = Assertions.assertDoesNotThrow(() -> updateWineImpl.updateWine(WINE_ID, wineDomain));
        Assertions.assertNotNull(updatedWine);
        Assertions.assertEquals(wineEntity, updatedWine);
        verify(updateWineRepository).updateWine(WINE_ID, wineDomain);
    }

    private WineEntity createWineEntity() {
        return WineEntity.builder()
                .id(UUID.fromString(WINE_ID))
                .sku("MiREDR2020It")
                .name("Miliasso Barolo DOCG 2020")
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent("14.5%")
                .volumeMl(750)
                .grape("Nebbiolo")
                .winery("Cantine Pover")
                .serviceTemperature("16-18°C")
                .price(new BigDecimal("150.00"))
                .purchaseLocation("Vinhos do Mundo")
                .purchaseDate(LocalDate.of(2021, 10, 10))
                .country("Italy")
                .region("Piemonte")
                .harvest("2020")
                .guardTime("10 years")
                .maturation("24 months in oak barrels")
                .harmonization("Red meats and mature cheeses")
                .build();
    }

    private WineDomain createWineDomain() {
        return WineDomain.builder()
                .name("Miliasso Barolo DOCG 2020")
                .wineDetails(
                        WineDetails.builder()
                                .wineType(EnumWineType.REDWINE)
                                .wineClassification(EnumWineClassification.DRYWINE)
                                .alcoholContent("14.5%")
                                .volumeMl(750)
                                .grape("Nebbiolo")
                                .winery("Cantine Povero")
                                .serviceTemperature("16-18°C")
                                .build()
                )
                .purchaseInfo(
                        PurchaseInfo.builder()
                                .price(new BigDecimal("150.00"))
                                .purchaseLocation("Vinhos do Mundo")
                                .purchaseDate(LocalDate.of(2021, 10, 10))
                                .build()
                )
                .wineOrigin(
                        WineOrigin.builder()
                                .country("Italy")
                                .region("Piemonte")
                                .harvest("2020")
                                .guardTime("10 years")
                                .maturation("24 months in oak barrels")
                                .harmonization("Red meats and mature cheeses")
                                .build()
                )
                .build();
    }
}