package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.gateways.mappers.WineEntityMapper;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateWineRepositoryJPATest {

    @InjectMocks
    private CreateWineRepositoryJPA createWineRepositoryJPA;

    @Mock
    private  WineRepository wineRepository;
    @Mock
    private WineEntityMapper wineEntityMapper;

    private WineDomain wineDomain;
    private WineEntity wineEntity;

    @BeforeEach
    void setUp() {
        wineDomain = createWineDomain();
        wineEntity = createWineEntity();
    }


    @Test
    @DisplayName("Deve persistir um vinho no banco de dados")
    void testDevePersistirUmVinhoNoBancoDeDados() {
        when(wineEntityMapper.toWineEntity(wineDomain)).thenReturn(wineEntity);
        when(wineRepository.save(wineEntity)).thenReturn(wineEntity);

        WineEntity wineEntitySaved = assertDoesNotThrow( () -> createWineRepositoryJPA.createWine(wineDomain));

        assertNotNull(wineEntitySaved);
        assertEquals(wineDomain.getSku(), wineEntitySaved.getSku());
        assertEquals(wineDomain.getName(), wineEntitySaved.getName());
        assertEquals(wineDomain.getWineDetails().getWineType(), wineEntitySaved.getWineType());
        assertEquals(wineDomain.getWineDetails().getWineClassification(), wineEntitySaved.getWineClassification());
        assertEquals(wineDomain.getWineDetails().getAlcoholContent(), wineEntitySaved.getAlcoholContent());
        assertEquals(wineDomain.getWineDetails().getVolumeMl(), wineEntitySaved.getVolumeMl());
        assertEquals(wineDomain.getWineDetails().getGrape(), wineEntitySaved.getGrape());
        assertEquals(wineDomain.getWineDetails().getWinery(), wineEntitySaved.getWinery());
        assertEquals(wineDomain.getWineDetails().getServiceTemperature(), wineEntitySaved.getServiceTemperature());
        assertEquals(wineDomain.getPurchaseInfo().getPrice(), wineEntitySaved.getPrice());
        assertEquals(wineDomain.getPurchaseInfo().getPurchaseLocation(), wineEntitySaved.getPurchaseLocation());
        assertEquals(wineDomain.getPurchaseInfo().getPurchaseDate(), wineEntitySaved.getPurchaseDate());
        assertEquals(wineDomain.getWineOrigin().getCountry(), wineEntitySaved.getCountry());
        assertEquals(wineDomain.getWineOrigin().getRegion(), wineEntitySaved.getRegion());
        assertEquals(wineDomain.getWineOrigin().getHarvest(), wineEntitySaved.getHarvest());
        assertEquals(wineDomain.getWineOrigin().getGuardTime(), wineEntitySaved.getGuardTime());
        assertEquals(wineDomain.getWineOrigin().getMaturation(), wineEntitySaved.getMaturation());
        assertEquals(wineDomain.getWineOrigin().getHarmonization(), wineEntitySaved.getHarmonization());

        verify(wineEntityMapper).toWineEntity(wineDomain);
        verify(wineRepository).save(wineEntity);
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
                .purchaseDate(LocalDate.now())
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
                .sku("Miliasso Barolo DOCG 2020", EnumWineType.REDWINE, EnumWineClassification.DRYWINE, "2020", "Italy")
                .name("Miliasso Barolo DOCG 2020")
                .wineDetails(WineDetails.builder()
                        .wineType(EnumWineType.REDWINE)
                        .wineClassification(EnumWineClassification.DRYWINE)
                        .alcoholContent("14.5%")
                        .volumeMl(750)
                        .grape("Nebbiolo")
                        .winery("Cantine Pover")
                        .serviceTemperature("16-18°C")
                        .build()
                )
                .purchaseInfo(PurchaseInfo.builder()
                        .price(new BigDecimal("150.00"))
                        .purchaseLocation("Vinhos do Mundo")
                        .purchaseDate(LocalDate.now())
                        .build()
                )
                .wineOrigin(WineOrigin.builder()
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
