package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.UpdateWineRepository;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.PurchaseInfoDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineDetailsDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineOriginDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.utils.EnumConverter;
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

    private WineEntity wineEntity;
    private WineInputDTO wineInputDTO;

    @BeforeEach
    void setUp() {
        wineEntity = createWineEntity();
        wineInputDTO = createWineInputDTO();
    }

    @Test
    @DisplayName("Deve atualizar um vinho pelo id")
    void testUpdateWine() {
        when(updateWineRepository.updateWine(WINE_ID, wineInputDTO)).thenReturn(wineEntity);

        WineEntity updatedWine = Assertions.assertDoesNotThrow(() -> updateWineImpl.updateWine(WINE_ID, wineInputDTO));
        Assertions.assertNotNull(updatedWine);
        Assertions.assertEquals(wineEntity, updatedWine);
        verify(updateWineRepository).updateWine(WINE_ID, wineInputDTO);
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

     private WineInputDTO createWineInputDTO() {
        return new WineInputDTO(
                "Miliasso Barolo DOCG 2020",
                new WineDetailsDTO(
                        EnumConverter.toString(EnumWineType.REDWINE),
                        EnumConverter.toString(EnumWineClassification.DRYWINE),
                        "14.5%",
                        "750",
                        "Nebbiolo",
                        "Cantine Pover",
                        "16-18°C"
                ),
                new PurchaseInfoDTO(
                        new BigDecimal("150.00"),
                        "Vinhos do Mundo",
                        LocalDate.now()
                ),
                new WineOriginDTO(
                        "Italy",
                        "Piemonte",
                        "2020",
                        "10 years",
                        "24 months in oak barrels",
                        "Red meats and mature cheeses"
                )
        );
    }

}