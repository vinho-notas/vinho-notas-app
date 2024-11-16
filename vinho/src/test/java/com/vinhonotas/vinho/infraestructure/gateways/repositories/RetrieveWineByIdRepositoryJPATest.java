package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveWineByIdRepositoryJPATest {

    @InjectMocks
    private RetrieveWineByIdRepositoryJPA retrieveWineByIdRepositoryJPA;

    @Mock
    private WineRepository wineRepository;

    WineEntity wineEntity;

    @BeforeEach
    void setUp() {
        wineEntity = createWineEntity();
    }

    @Test
    @DisplayName("Deve retornar um vinho pelo id")
    void testDeveRetornarUmVinhoPeloId() {
        when(wineRepository.findById(UUID.fromString("f5b1f1b1-0b1b-4b1b-8b1b-1b1b1b1b1b1b"))).thenReturn(Optional.ofNullable(wineEntity));

        WineEntity wineEntityRetrieved = assertDoesNotThrow(() -> retrieveWineByIdRepositoryJPA.retrieveWineById("f5b1f1b1-0b1b-4b1b-8b1b-1b1b1b1b1b1b"));

        assertNotNull(wineEntityRetrieved);
        assertEquals(wineEntity.getId(), wineEntityRetrieved.getId());
        assertEquals(wineEntity.getSku(), wineEntityRetrieved.getSku());
        assertEquals(wineEntity.getName(), wineEntityRetrieved.getName());
        assertEquals(wineEntity.getWineType(), wineEntityRetrieved.getWineType());
        assertEquals(wineEntity.getWineClassification(), wineEntityRetrieved.getWineClassification());
        assertEquals(wineEntity.getAlcoholContent(), wineEntityRetrieved.getAlcoholContent());
        assertEquals(wineEntity.getVolumeMl(), wineEntityRetrieved.getVolumeMl());
        assertEquals(wineEntity.getGrape(), wineEntityRetrieved.getGrape());
        assertEquals(wineEntity.getWinery(), wineEntityRetrieved.getWinery());
        assertEquals(wineEntity.getServiceTemperature(), wineEntityRetrieved.getServiceTemperature());
        assertEquals(wineEntity.getPrice(), wineEntityRetrieved.getPrice());
        assertEquals(wineEntity.getPurchaseLocation(), wineEntityRetrieved.getPurchaseLocation());
        assertEquals(wineEntity.getPurchaseDate(), wineEntityRetrieved.getPurchaseDate());
        assertEquals(wineEntity.getCountry(), wineEntityRetrieved.getCountry());
        assertEquals(wineEntity.getRegion(), wineEntityRetrieved.getRegion());
        assertEquals(wineEntity.getHarvest(), wineEntityRetrieved.getHarvest());
        assertEquals(wineEntity.getGuardTime(), wineEntityRetrieved.getGuardTime());
        assertEquals(wineEntity.getMaturation(), wineEntityRetrieved.getMaturation());
        assertEquals(wineEntity.getHarmonization(), wineEntityRetrieved.getHarmonization());

        verify(wineRepository).findById(UUID.fromString("f5b1f1b1-0b1b-4b1b-8b1b-1b1b1b1b1b1b"));
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar retornar um vinho pelo id que não existe")
    void testDeveLancarUmaExcecaoAoTentarRetornarUmVinhoPeloIdQueNaoExiste() {
        when(wineRepository.findById(UUID.fromString("f5b1f1b1-0b1b-4b1b-8b1b-1b1b1b1b1b1b"))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> retrieveWineByIdRepositoryJPA.retrieveWineById("f5b1f1b1-0b1b-4b1b-8b1b-1b1b1b1b1b1b"));

        assertEquals(MessagesConstants.ERROR_WINE_NOT_FOUND, exception.getMessage());
        verify(wineRepository).findById(UUID.fromString("f5b1f1b1-0b1b-4b1b-8b1b-1b1b1b1b1b1b"));
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