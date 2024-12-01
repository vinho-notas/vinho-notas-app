package com.vinhonotas.vinho.infraestructure.gateways.repositories;

import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.PurchaseInfoDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineDetailsDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineInputDTO;
import com.vinhonotas.vinho.infraestructure.controller.dtos.input.WineOriginDTO;
import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import com.vinhonotas.vinho.infraestructure.persistence.WineRepository;
import com.vinhonotas.vinho.utils.EnumConverter;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
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
    private WineInputDTO wineInputDTO;

    @BeforeEach
    void setUp() {
        wineEntity = createWineEntity();
        wineInputDTO = createWineInputDTO();
    }

    @Test
    @DisplayName("Deve atualizar um vinho no banco de dados")
    void testUpdateSuccess() {
        when(wineRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(wineEntity));
        when(wineRepository.save(wineEntity)).thenReturn(wineEntity);

        WineEntity wineEntityUpdated = assertDoesNotThrow(() -> updateWineRepositoryJPA.updateWine(ID, wineInputDTO));

        assertNotNull(wineEntityUpdated);
        assertEquals(wineInputDTO.name(), wineEntityUpdated.getName());
        assertEquals(EnumConverter.fromString(wineInputDTO.wineDetails().wineType(), EnumWineType.class), wineEntityUpdated.getWineType());
        assertEquals(EnumConverter.fromString(wineInputDTO.wineDetails().wineClassification(), EnumWineClassification.class),
                wineEntityUpdated.getWineClassification());
        assertEquals(wineInputDTO.wineDetails().alcoholContent(), wineEntityUpdated.getAlcoholContent());
        assertEquals(wineInputDTO.wineDetails().volumeMl(), wineEntityUpdated.getVolumeMl().toString());
        assertEquals(wineInputDTO.wineDetails().grape(), wineEntityUpdated.getGrape());
        assertEquals(wineInputDTO.wineDetails().winery(), wineEntityUpdated.getWinery());
        assertEquals(wineInputDTO.wineDetails().serviceTemperature(), wineEntityUpdated.getServiceTemperature());
        assertEquals(wineInputDTO.purchaseInfo().price(), wineEntityUpdated.getPrice());
        assertEquals(wineInputDTO.purchaseInfo().purchaseLocation(), wineEntityUpdated.getPurchaseLocation());
        assertEquals(wineInputDTO.purchaseInfo().purchaseDate(), wineEntityUpdated.getPurchaseDate());
        assertEquals(wineInputDTO.wineOrigin().country(), wineEntityUpdated.getCountry());
        assertEquals(wineInputDTO.wineOrigin().region(), wineEntityUpdated.getRegion());
        assertEquals(wineInputDTO.wineOrigin().harvest(), wineEntityUpdated.getHarvest());
        assertEquals(wineInputDTO.wineOrigin().guardTime(), wineEntityUpdated.getGuardTime());
        assertEquals(wineInputDTO.wineOrigin().maturation(), wineEntityUpdated.getMaturation());
        assertEquals(wineInputDTO.wineOrigin().harmonization(), wineEntityUpdated.getHarmonization());
        verify(wineRepository).findById(UUID.fromString(ID));
        verify(wineRepository).save(wineEntity);
    }

    @Test
    @DisplayName("Deve lançar uma WineNotFoundException ao tentar atualizar um vinho que não existe")
    void testUpdateWineNotFound() {
        when(wineRepository.findById(UUID.fromString(ID))).thenReturn(Optional.ofNullable(null));

        Exception exception = assertThrows(Exception.class, () -> updateWineRepositoryJPA.updateWine(ID, wineInputDTO));
        assertEquals(MessagesConstants.ERROR_WINE_NOT_FOUND, exception.getMessage());
        verify(wineRepository).findById(UUID.fromString(ID));
        verify(wineRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Deve lançar uma BadRequestException ao tentar atualizar um vinho com dados inválidos")
    void testUpdateBadRequest() {
        when(wineRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(wineEntity));
        doThrow(new RuntimeException()).when(wineRepository).save(wineEntity);

        Exception exception = assertThrows(Exception.class, () -> updateWineRepositoryJPA.updateWine(ID, wineInputDTO));
        assertEquals(MessagesConstants.ERROR_UPDATE_WINE_DATA, exception.getMessage());
        verify(wineRepository).findById(UUID.fromString(ID));
        verify(wineRepository).save(wineEntity);
    }

    @Test
    @DisplayName("Deve atualizar as informações de compra corretamente")
    void testUpdatePurchaseInfo() throws Exception {
        UpdateWineRepositoryJPA updateWineRepositoryJPAReflection = new UpdateWineRepositoryJPA(wineRepository);
        Method method = UpdateWineRepositoryJPA.class.getDeclaredMethod("updatePurchaseInfo", WineEntity.class, WineInputDTO.class);
        method.setAccessible(true);

        WineEntity wineEntityPurchaseInfo = createWineEntity();
        PurchaseInfoDTO purchaseInfo = new PurchaseInfoDTO(new BigDecimal("200.00"), "Loja Nova", LocalDate.now());
        WineInputDTO wineInputDTOPurchaseInfo= new WineInputDTO(null, null, purchaseInfo, null);

        assertDoesNotThrow(() -> method.invoke(updateWineRepositoryJPAReflection, wineEntityPurchaseInfo, wineInputDTOPurchaseInfo));

        assertEquals(new BigDecimal("200.00"), wineEntityPurchaseInfo.getPrice());
        assertEquals("Loja Nova", wineEntityPurchaseInfo.getPurchaseLocation());
        assertEquals(LocalDate.now(), wineEntityPurchaseInfo.getPurchaseDate());
    }

    @Test
    @DisplayName("Deve atualizar as informações de origem do vinho corretamente")
    void testUpdateWineDetails() throws Exception {
        UpdateWineRepositoryJPA updateWineRepositoryJPAReflection = new UpdateWineRepositoryJPA(wineRepository);
        Method method = UpdateWineRepositoryJPA.class.getDeclaredMethod("updateWineDetails", WineEntity.class, WineInputDTO.class);
        method.setAccessible(true);

        WineEntity wineEntityWineDetails = createWineEntity();
        WineDetailsDTO wineDetails = new WineDetailsDTO(
                EnumConverter.toString(EnumWineType.ROSEWINE),
                EnumConverter.toString(EnumWineClassification.SWEETWINE),
                "12.5%",
                "500",
                "Merlot",
                "Cantine Pover",
                "10-12°C"
        );
        WineInputDTO wineInputDTOWineDetails = new WineInputDTO(null, wineDetails, null, null);

        assertDoesNotThrow(() -> method.invoke(updateWineRepositoryJPAReflection, wineEntityWineDetails, wineInputDTOWineDetails));

        assertEquals(EnumWineType.ROSEWINE, wineEntityWineDetails.getWineType());
        assertEquals(EnumWineClassification.SWEETWINE, wineEntityWineDetails.getWineClassification());
        assertEquals("12.5%", wineEntityWineDetails.getAlcoholContent());
        assertEquals("500", wineEntityWineDetails.getVolumeMl().toString());
        assertEquals("Merlot", wineEntityWineDetails.getGrape());
        assertEquals("Cantine Pover", wineEntityWineDetails.getWinery());
        assertEquals("10-12°C", wineEntityWineDetails.getServiceTemperature());
    }

    @Test
    @DisplayName("Deve atualizar as informações de origem do vinho corretamente")
    void testUpdateWineOrigin() throws Exception {
        UpdateWineRepositoryJPA updateWineRepositoryJPAReflection = new UpdateWineRepositoryJPA(wineRepository);
        Method method = UpdateWineRepositoryJPA.class.getDeclaredMethod("updateWineOrigin", WineEntity.class, WineInputDTO.class);
        method.setAccessible(true);

        WineEntity wineEntityWineOrigin = createWineEntity();
        WineOriginDTO wineOrigin = new WineOriginDTO(
                "France",
                "Bordeaux",
                "2019",
                "5 years",
                "12 months in oak barrels",
                "White meats and soft cheeses"
        );
        WineInputDTO wineInputDTOWineOrigin = new WineInputDTO(null, null, null, wineOrigin);

        assertDoesNotThrow(() -> method.invoke(updateWineRepositoryJPAReflection, wineEntityWineOrigin, wineInputDTOWineOrigin));

        assertEquals("France", wineEntityWineOrigin.getCountry());
        assertEquals("Bordeaux", wineEntityWineOrigin.getRegion());
        assertEquals("2019", wineEntityWineOrigin.getHarvest());
        assertEquals("5 years", wineEntityWineOrigin.getGuardTime());
        assertEquals("12 months in oak barrels", wineEntityWineOrigin.getMaturation());
        assertEquals("White meats and soft cheeses", wineEntityWineOrigin.getHarmonization());
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
