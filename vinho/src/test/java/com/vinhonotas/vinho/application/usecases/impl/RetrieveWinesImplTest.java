package com.vinhonotas.vinho.application.usecases.impl;

import com.vinhonotas.vinho.application.gateways.RetrieveWinesRepository;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.junit.jupiter.api.Assertions;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveWinesImplTest {

    @InjectMocks
    private RetrieveWinesImpl retrieveWinesImpl;

    @Mock
    private RetrieveWinesRepository retrieveWinesRepository;

    @Test
    @DisplayName("Deve retornar uma lista de vinhos")
    void testRetrieveAllWines() {
        WineEntity wineEntity = createWine();

        when(retrieveWinesRepository.retrieveAllWines())
                .thenReturn(List.of(wineEntity));

        List<WineEntity> list = retrieveWinesImpl.retrieveAllWines();
        Assertions.assertNotNull(list);
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(1, list.size());
        verify(retrieveWinesRepository).retrieveAllWines();
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar recuperar uma lista de vinhos vazia")
    void testRetrieveAllWinesThrowException() {
        when(retrieveWinesRepository.retrieveAllWines())
                .thenReturn(List.of());

        Exception exception = Assertions.assertThrows(Exception.class, () -> retrieveWinesImpl.retrieveAllWines());
        Assertions.assertEquals(MessagesConstants.ERROR_WINE_NOT_FOUND, exception.getMessage());
        verify(retrieveWinesRepository).retrieveAllWines();
    }

    private WineEntity createWine() {
        return WineEntity.builder()
                .id(UUID.fromString("562aaf01-f0c6-4bd6-aa22-30b4596e217f"))
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

}
