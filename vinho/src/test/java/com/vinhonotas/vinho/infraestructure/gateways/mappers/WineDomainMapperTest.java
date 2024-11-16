package com.vinhonotas.vinho.infraestructure.gateways.mappers;

import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.PurchaseInfoDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineDetailsDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineOriginDTO;
import com.vinhonotas.vinho.utils.EnumConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class WineDomainMapperTest {

    @InjectMocks
    private WineDomainMapper wineDomainMapper;

    private WineInputDTO wineInputDTO;

    @BeforeEach
    void setUp() {
        wineInputDTO = createWineInputDTO();
    }

    @Test
    void testToWineDomainInputNotNull() {
        WineDomain wineDomain = Assertions.assertDoesNotThrow(() -> wineDomainMapper.toWineDomain(wineInputDTO));

        Assertions.assertNotNull(wineDomain);
        Assertions.assertNotNull(wineDomain.getWineDetails());
        Assertions.assertNotNull(wineDomain.getPurchaseInfo());
        Assertions.assertNotNull(wineDomain.getWineOrigin());
    }

    @Test
    void testWineDomainInputNull() {
        WineDomain wineDomain = Assertions.assertDoesNotThrow(() -> wineDomainMapper.toWineDomain(null));

        Assertions.assertNull(wineDomain);
    }

    private WineInputDTO createWineInputDTO() {
        return WineInputDTO.builder()
                .name("Miliasso Barolo DOCG 2020")
                .wineDetails(createWineDetailsDTO())
                .purchaseInfo(createPurchaseInfoDTO())
                .wineOrigin(createWineOriginDTO())
                .build();
    }

    private WineDetailsDTO createWineDetailsDTO() {
        return WineDetailsDTO.builder()
                .wineType(EnumConverter.toString(EnumWineType.REDWINE))
                .wineClassification(EnumConverter.toString(EnumWineClassification.DRYWINE))
                .alcoholContent("14.5%")
                .volumeMl("750")
                .grape("Nebbiolo")
                .winery("Cantine Pover")
                .serviceTemperature("16-18°C")
                .build();
    }

    private PurchaseInfoDTO createPurchaseInfoDTO() {
        return PurchaseInfoDTO.builder()
                .price(new BigDecimal("150.00"))
                .purchaseLocation("Vinhos do Mundo")
                .purchaseDate(LocalDate.now())
                .build();
    }

    private WineOriginDTO createWineOriginDTO() {
        return WineOriginDTO.builder()
                .country("Italy")
                .region("Piemonte")
                .harvest("2020")
                .guardTime("10 years")
                .maturation("24 months in oak barrels")
                .harmonization("Red meats and mature cheeses")
                .build();
    }

}