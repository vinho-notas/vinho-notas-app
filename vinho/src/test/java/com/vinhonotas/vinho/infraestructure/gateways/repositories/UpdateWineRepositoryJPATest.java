package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.domain.entities.wine.PurchaseInfo;
import com.vinhonotas.vinho.domain.entities.wine.WineDetails;
import com.vinhonotas.vinho.domain.entities.wine.WineDomain;
import com.vinhonotas.vinho.domain.entities.wine.WineOrigin;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateWineRepositoryJPATest {

    @InjectMocks
    private UpdateWineRepositoryJPA updateWineRepositoryJPA;

    private static final String ID = "f5b1f1b1-0b1b-4b1b-8b1b-1b1b1b1b1b1b";

    @Mock
    private WineRepository wineRepository;

    private WineEntity wineEntity;
    private WineDomain wineDomain;

    @BeforeEach
    void setUp() {
        wineEntity = createWineEntity();
        wineDomain = createWineDomain();
    }

    @Test
    @DisplayName("Deve atualizar um vinho no banco de dados")
    void testUpdateSuccess() {
        when(wineRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(wineEntity));
        when(wineRepository.save(wineEntity)).thenReturn(wineEntity);

        WineEntity wineEntityUpdated = assertDoesNotThrow(() -> updateWineRepositoryJPA.updateWine(ID, wineDomain));

        assertNotNull(wineEntityUpdated);
        assertEquals(wineDomain.getSku(), wineEntityUpdated.getSku());
        assertEquals(wineDomain.getName(), wineEntityUpdated.getName());
        assertEquals(wineDomain.getWineDetails().getWineType(), wineEntityUpdated.getWineType());
        assertEquals(wineDomain.getWineDetails().getWineClassification(), wineEntityUpdated.getWineClassification());
        assertEquals(wineDomain.getWineDetails().getAlcoholContent(), wineEntityUpdated.getAlcoholContent());
        assertEquals(wineDomain.getWineDetails().getVolumeMl(), wineEntityUpdated.getVolumeMl());
        assertEquals(wineDomain.getWineDetails().getGrape(), wineEntityUpdated.getGrape());
        assertEquals(wineDomain.getWineDetails().getWinery(), wineEntityUpdated.getWinery());
        assertEquals(wineDomain.getWineDetails().getServiceTemperature(), wineEntityUpdated.getServiceTemperature());
        assertEquals(wineDomain.getPurchaseInfo().getPrice(), wineEntityUpdated.getPrice());
        assertEquals(wineDomain.getPurchaseInfo().getPurchaseLocation(), wineEntityUpdated.getPurchaseLocation());
        assertEquals(wineDomain.getPurchaseInfo().getPurchaseDate(), wineEntityUpdated.getPurchaseDate());
        assertEquals(wineDomain.getWineOrigin().getCountry(), wineEntityUpdated.getCountry());
        assertEquals(wineDomain.getWineOrigin().getRegion(), wineEntityUpdated.getRegion());
        assertEquals(wineDomain.getWineOrigin().getHarvest(), wineEntityUpdated.getHarvest());
        assertEquals(wineDomain.getWineOrigin().getGuardTime(), wineEntityUpdated.getGuardTime());
        assertEquals(wineDomain.getWineOrigin().getMaturation(), wineEntityUpdated.getMaturation());
        assertEquals(wineDomain.getWineOrigin().getHarmonization(), wineEntityUpdated.getHarmonization());
        verify(wineRepository).findById(UUID.fromString(ID));
        verify(wineRepository).save(wineEntity);
    }

    @Test
    @DisplayName("Deve lançar uma WineNotFoundException ao tentar atualizar um vinho que não existe")
    void testUpdateWineNotFound() {
        when(wineRepository.findById(UUID.fromString(ID))).thenReturn(Optional.ofNullable(null));

        Exception exception = assertThrows(Exception.class, () -> updateWineRepositoryJPA.updateWine(ID, wineDomain));
        assertEquals(MessagesConstants.ERROR_WINE_NOT_FOUND, exception.getMessage());
        verify(wineRepository).findById(UUID.fromString(ID));
        verify(wineRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Deve lançar uma BadRequestException ao tentar atualizar um vinho com dados inválidos")
    void testUpdateBadRequest() {
        when(wineRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(wineEntity));
        doThrow(new RuntimeException()).when(wineRepository).save(wineEntity);

        Exception exception = assertThrows(Exception.class, () -> updateWineRepositoryJPA.updateWine(ID, wineDomain));
        assertEquals(MessagesConstants.ERROR_UPDATE_WINE_DATA, exception.getMessage());
        verify(wineRepository).findById(UUID.fromString(ID));
        verify(wineRepository).save(wineEntity);
    }

    private WineEntity createWineEntity() {
        return WineEntity.builder()
                .id(UUID.fromString(ID))
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
                .wineDetails(createWineDetails())
                .purchaseInfo(createPurchaseInfo())
                .wineOrigin(createWineOrigin())
                .build();
    }

    private WineOrigin createWineOrigin() {
        return WineOrigin.builder()
                .country("Italy")
                .region("Piemonte")
                .harvest("2020")
                .guardTime("10 years")
                .maturation("24 months in oak barrels")
                .harmonization("Red meats and mature cheeses")
                .build();
    }

    private PurchaseInfo createPurchaseInfo() {
        return PurchaseInfo.builder()
                .price(new BigDecimal("150.00"))
                .purchaseLocation("Vinhos do Mundo")
                .purchaseDate(LocalDate.now())
                .build();
    }

    private WineDetails createWineDetails() {
        return WineDetails.builder()
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent("14.5%")
                .volumeMl(750)
                .grape("Nebbiolo")
                .winery("Cantine Pover")
                .serviceTemperature("16-18°C")
                .build();
    }

}
