package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.CreateWineRepository;
import com.vinhonotas.vinho.domain.entities.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateWineImplTest {

    @InjectMocks
    private CreateWineImpl createWineImpl;

    @Mock
    private CreateWineRepository createWineRepository;

    private WineDomain wineDomain;
    private WineEntity wineEntity;

    @BeforeEach
    void setUp() {
        wineDomain = createWineDomain();
        wineEntity = createWineEntity();
    }

    @Test
    @DisplayName("Deve criar um vinho")
    void testCreateWine() {
        Mockito.when(createWineRepository.createWine(wineDomain)).thenReturn(wineEntity);

        WineEntity wineCreated = assertDoesNotThrow(() -> createWineImpl.createWine(wineDomain));
        assertNotNull(wineCreated);
        assertEquals(wineEntity, wineCreated);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar criar um vinho")
    void testCreateWineThrowBadRequestException() {
        Mockito.when(createWineRepository.createWine(wineDomain)).thenThrow(new BadRequestException(MessagesConstants.ERROR_CREATE_WINE));

        Exception ex = assertThrows(Exception.class, () -> createWineImpl.createWine(wineDomain));
        assertEquals(MessagesConstants.ERROR_CREATE_WINE, ex.getMessage());
    }

    private WineEntity createWineEntity() {
        return WineEntity.builder()
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