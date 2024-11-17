package com.vinhonotas.vinho.infraestructure.gateways.mappers;

import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.controller.dtos.output.WineOutputDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.utils.EnumConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WineEntityMapperTest {

    @InjectMocks
    private WineEntityMapper wineEntityMapper;

    private WineDomain wineDomain;
    private WineEntity wineEntity;

    @BeforeEach
    void setUp() {
        wineDomain = createWineDomain();
        wineEntity = createWineEntity();
    }

    @Test
    @DisplayName("Deve converter um WineDomain para WineEntity")
    void testToWineEntity() {
        WineEntity wineEntityConverted = assertDoesNotThrow(() -> wineEntityMapper.toWineEntity(wineDomain));

        assertNotNull(wineEntityConverted);
        assertEquals(wineDomain.getSku(), wineEntityConverted.getSku());
        assertEquals(wineDomain.getName(), wineEntityConverted.getName());
        assertEquals(wineDomain.getPurchaseInfo().getPrice(), wineEntityConverted.getPrice());
        assertEquals(wineDomain.getPurchaseInfo().getPurchaseLocation(), wineEntityConverted.getPurchaseLocation());
        assertEquals(wineDomain.getPurchaseInfo().getPurchaseDate(), wineEntityConverted.getPurchaseDate());
        assertEquals(wineDomain.getWineDetails().getWineType(), wineEntityConverted.getWineType());
        assertEquals(wineDomain.getWineDetails().getWineClassification(), wineEntityConverted.getWineClassification());
        assertEquals(wineDomain.getWineDetails().getAlcoholContent(), wineEntityConverted.getAlcoholContent());
        assertEquals(wineDomain.getWineDetails().getVolumeMl(), wineEntityConverted.getVolumeMl());
        assertEquals(wineDomain.getWineDetails().getGrape(), wineEntityConverted.getGrape());
        assertEquals(wineDomain.getWineDetails().getWinery(), wineEntityConverted.getWinery());
        assertEquals(wineDomain.getWineDetails().getServiceTemperature(), wineEntityConverted.getServiceTemperature());
        assertEquals(wineDomain.getWineOrigin().getHarvest(), wineEntityConverted.getHarvest());
        assertEquals(wineDomain.getWineOrigin().getCountry(), wineEntityConverted.getCountry());
        assertEquals(wineDomain.getWineOrigin().getGuardTime(), wineEntityConverted.getGuardTime());
        assertEquals(wineDomain.getWineOrigin().getRegion(), wineEntityConverted.getRegion());
        assertEquals(wineDomain.getWineOrigin().getMaturation(), wineEntityConverted.getMaturation());
        assertEquals(wineDomain.getWineOrigin().getHarmonization(), wineEntityConverted.getHarmonization());
    }

    @Test
    @DisplayName("Deve retornar null ao converter um WineDomain nulo para WineEntity")
    void testToWineEntityNull() {
        WineEntity wineEntityConverted = assertDoesNotThrow(() -> wineEntityMapper.toWineEntity(null));

        assertNull(wineEntityConverted);
    }

    @Test
    @DisplayName("Deve converter um WineEntity para WineOutputDTO")
    void testToWineOutputDTO() {
        WineOutputDTO wineOutputDTOConverted = assertDoesNotThrow(() -> wineEntityMapper.toWineOutputDTO(wineEntity));

        assertNotNull(wineOutputDTOConverted);
        assertEquals(wineEntity.getId(), wineOutputDTOConverted.getId());
        assertEquals(wineEntity.getSku(), wineOutputDTOConverted.getSku());
        assertEquals(wineEntity.getName(), wineOutputDTOConverted.getName());
        assertEquals(wineEntity.getPrice(), wineOutputDTOConverted.getPrice());
        assertEquals(wineEntity.getPurchaseLocation(), wineOutputDTOConverted.getPurchaseLocation());
        assertEquals(wineEntity.getPurchaseDate(), wineOutputDTOConverted.getPurchaseDate());
        assertEquals(EnumConverter.toString(wineEntity.getWineType()), wineOutputDTOConverted.getWineType());
        assertEquals(EnumConverter.toString(wineEntity.getWineClassification()), wineOutputDTOConverted.getWineClassification());
        assertEquals(wineEntity.getAlcoholContent(), wineOutputDTOConverted.getAlcoholContent());
        assertEquals(wineEntity.getVolumeMl(), Integer.parseInt(wineOutputDTOConverted.getVolumeMl()));
        assertEquals(wineEntity.getGrape(), wineOutputDTOConverted.getGrape());
        assertEquals(wineEntity.getWinery(), wineOutputDTOConverted.getWinery());
        assertEquals(wineEntity.getServiceTemperature(), wineOutputDTOConverted.getServiceTemperature());
        assertEquals(wineEntity.getHarvest(), wineOutputDTOConverted.getHarvest());
        assertEquals(wineEntity.getCountry(), wineOutputDTOConverted.getCountry());
        assertEquals(wineEntity.getGuardTime(), wineOutputDTOConverted.getGuardTime());
        assertEquals(wineEntity.getRegion(), wineOutputDTOConverted.getRegion());
        assertEquals(wineEntity.getMaturation(), wineOutputDTOConverted.getMaturation());
        assertEquals(wineEntity.getHarmonization(), wineOutputDTOConverted.getHarmonization());
    }

    @Test
    @DisplayName("Deve retornar null ao converter um WineEntity nulo para WineOutputDTO")
    void testToWineOutputDTONull() {
        WineOutputDTO wineOutputDTOConverted = assertDoesNotThrow(() -> wineEntityMapper.toWineOutputDTO(null));

        assertNull(wineOutputDTOConverted);
    }

    @Test
    @DisplayName("Deve converter uma lista de WineEntity para uma lista de WineOutputDTO")
    void testToWineOutputDTOList() {
        List<WineEntity> wineEntities = List.of(wineEntity, wineEntity, wineEntity);

        List<WineOutputDTO> wineOutputDTOList = assertDoesNotThrow(() -> wineEntityMapper.toWineOutputDTOList(wineEntities));

        assertNotNull(wineOutputDTOList);
        assertFalse(wineOutputDTOList.isEmpty());
        assertEquals(wineEntities.size(), wineOutputDTOList.size());
    }

    private WineEntity createWineEntity() {
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