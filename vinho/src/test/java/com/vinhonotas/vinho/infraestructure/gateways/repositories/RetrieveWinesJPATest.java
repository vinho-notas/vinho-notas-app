package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveWinesJPATest {

    @InjectMocks
    private RetrieveWinesJPA retrieveWinesJPA;

    @Mock
    private WineRepository wineRepository;

    @Test
    @DisplayName("Deve retornar uma lista de vinhos")
    void testRetrieveAllWines() {
        WineEntity wineEntity = createWineEntity();

        when(wineRepository.findAll()).thenReturn(List.of(wineEntity));
        List<WineEntity> list = assertDoesNotThrow(() -> retrieveWinesJPA.retrieveAllWines());

        assertNotNull(list);
        assertFalse(list.isEmpty());
        verify(wineRepository).findAll();
    }

    private WineEntity createWineEntity() {
        return WineEntity.builder()
                .id(UUID.fromString("f5b1f1b1-0b1b-4b1b-8b1b-1b1b1b1b1b1b"))
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
                .purchaseDate(LocalDate.now())
                .country("Italy")
                .region("Piemonte")
                .harvest("2020")
                .guardTime("10 years")
                .maturation("24 months in oak barrels")
                .harmonization("Red meats and mature cheeses")
                .build();
    }

}