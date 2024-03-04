package com.vinhonotas.bff.application.services.vinho.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.vinho.WineClient;
import com.vinhonotas.bff.domain.enums.EnumWineClassification;
import com.vinhonotas.bff.domain.enums.EnumWineType;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.vinho.WineOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WineServiceImplTest {

    @InjectMocks
    private WineServiceImpl wineService;

    @Mock
    private WineClient wineClient;

    private WineInputDTO wineInputDTO;
    private WineOutputDTO wineOutputDTO;

    @BeforeEach
    void setUp() {
        wineInputDTO = createWineInputDTO();
        wineOutputDTO = createWineOutputDTO();
    }

    @Test
    @DisplayName("Deve criar um vinho")
    void testCreateWine() {
        when(wineService.createWine(wineInputDTO)).thenReturn(wineOutputDTO);
        WineOutputDTO wineCreated = assertDoesNotThrow(() -> wineService.createWine(wineInputDTO));

        assertNotNull(wineCreated);
        assertEquals(wineOutputDTO.getId(), wineCreated.getId());
        assertEquals(wineOutputDTO.getName(), wineCreated.getName());
        assertEquals(wineOutputDTO.getPrice(), wineCreated.getPrice());
        assertEquals(wineOutputDTO.getPurchaseLocation(), wineCreated.getPurchaseLocation());
        assertEquals(wineOutputDTO.getPurchaseDate(), wineCreated.getPurchaseDate());
        assertEquals(wineOutputDTO.getWineType(), wineCreated.getWineType());
        assertEquals(wineOutputDTO.getWineClassification(), wineCreated.getWineClassification());
        assertEquals(wineOutputDTO.getAlcoholContent(), wineCreated.getAlcoholContent());
        assertEquals(wineOutputDTO.getVolumeMl(), wineCreated.getVolumeMl());
        assertEquals(wineOutputDTO.getGrape(), wineCreated.getGrape());
        assertEquals(wineOutputDTO.getWinery(), wineCreated.getWinery());
        assertEquals(wineOutputDTO.getServiceTemperature(), wineCreated.getServiceTemperature());
        assertEquals(wineOutputDTO.getHarvest(), wineCreated.getHarvest());
        assertEquals(wineOutputDTO.getCountry(), wineCreated.getCountry());
        assertEquals(wineOutputDTO.getGuardTime(), wineCreated.getGuardTime());
        assertEquals(wineOutputDTO.getRegion(), wineCreated.getRegion());
        assertEquals(wineOutputDTO.getMaturation(), wineCreated.getMaturation());
        assertEquals(wineOutputDTO.getHarmonization(), wineCreated.getHarmonization());
        verify(wineClient).createWine(wineInputDTO);
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao criar um vinho")
    void testCreateWineThrowBadRequestException() {
        when(wineService.createWine(wineInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> wineService.createWine(wineInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(wineClient).createWine(wineInputDTO);
    }

    @Test
    @DisplayName("Deve listar todos os vinhos")
    void testGetAllWines() {
        when(wineClient.getAllWines()).thenReturn(List.of(wineOutputDTO));

        List<WineOutputDTO> wines = assertDoesNotThrow(() -> wineService.getAllWines());

        assertNotNull(wines);
        assertFalse(wines.isEmpty());
        assertEquals(wineOutputDTO.getId(), wines.get(0).getId());
        assertEquals(wineOutputDTO.getName(), wines.get(0).getName());
        assertEquals(wineOutputDTO.getPrice(), wines.get(0).getPrice());
        assertEquals(wineOutputDTO.getPurchaseLocation(), wines.get(0).getPurchaseLocation());
        assertEquals(wineOutputDTO.getPurchaseDate(), wines.get(0).getPurchaseDate());
        assertEquals(wineOutputDTO.getWineType(), wines.get(0).getWineType());
        assertEquals(wineOutputDTO.getWineClassification(), wines.get(0).getWineClassification());
        assertEquals(wineOutputDTO.getAlcoholContent(), wines.get(0).getAlcoholContent());
        assertEquals(wineOutputDTO.getVolumeMl(), wines.get(0).getVolumeMl());
        assertEquals(wineOutputDTO.getGrape(), wines.get(0).getGrape());
        assertEquals(wineOutputDTO.getWinery(), wines.get(0).getWinery());
        assertEquals(wineOutputDTO.getServiceTemperature(), wines.get(0).getServiceTemperature());
        assertEquals(wineOutputDTO.getHarvest(), wines.get(0).getHarvest());
        assertEquals(wineOutputDTO.getCountry(), wines.get(0).getCountry());
        assertEquals(wineOutputDTO.getGuardTime(), wines.get(0).getGuardTime());
        assertEquals(wineOutputDTO.getRegion(), wines.get(0).getRegion());
        assertEquals(wineOutputDTO.getMaturation(), wines.get(0).getMaturation());
        assertEquals(wineOutputDTO.getHarmonization(), wines.get(0).getHarmonization());
        verify(wineClient).getAllWines();
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao listar todos os vinhos")
    void testGetAllWinesThrowNotFoundException() {
        when(wineClient.getAllWines()).thenReturn(List.of());

        Exception exception = assertThrows(Exception.class, () -> wineService.getAllWines());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(wineClient).getAllWines();
    }

    @Test
    @DisplayName("Deve buscar um vinho pelo id")
    void testGetWineById() {
        when(wineClient.getWineById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(wineOutputDTO);

        WineOutputDTO wine = assertDoesNotThrow(() -> wineService.getWineById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));

        assertNotNull(wine);
        assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), wine.getId());
        assertEquals(wineOutputDTO.getName(), wine.getName());
        assertEquals(wineOutputDTO.getPrice(), wine.getPrice());
        assertEquals(wineOutputDTO.getPurchaseLocation(), wine.getPurchaseLocation());
        assertEquals(wineOutputDTO.getPurchaseDate(), wine.getPurchaseDate());
        assertEquals(wineOutputDTO.getWineType(), wine.getWineType());
        assertEquals(wineOutputDTO.getWineClassification(), wine.getWineClassification());
        assertEquals(wineOutputDTO.getAlcoholContent(), wine.getAlcoholContent());
        assertEquals(wineOutputDTO.getVolumeMl(), wine.getVolumeMl());
        assertEquals(wineOutputDTO.getGrape(), wine.getGrape());
        assertEquals(wineOutputDTO.getWinery(), wine.getWinery());
        assertEquals(wineOutputDTO.getServiceTemperature(), wine.getServiceTemperature());
        assertEquals(wineOutputDTO.getHarvest(), wine.getHarvest());
        assertEquals(wineOutputDTO.getCountry(), wine.getCountry());
        assertEquals(wineOutputDTO.getGuardTime(), wine.getGuardTime());
        assertEquals(wineOutputDTO.getRegion(), wine.getRegion());
        assertEquals(wineOutputDTO.getMaturation(), wine.getMaturation());
        assertEquals(wineOutputDTO.getHarmonization(), wine.getHarmonization());
        verify(wineClient).getWineById(wineOutputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao buscar um vinho pelo id")
    void testGetWineByIdThrowNotFoundException() {
        when(wineClient.getWineById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> wineService.getWineById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(wineClient).getWineById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
    }

    @Test
    @DisplayName("Deve atualizar um vinho")
    void testUpdateWine() {
        when(wineClient.updateWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", wineInputDTO)).thenReturn(wineOutputDTO);

        WineOutputDTO wineUpdated = assertDoesNotThrow(() -> wineService
                .updateWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", wineInputDTO));

        assertNotNull(wineUpdated);
        assertEquals(wineOutputDTO.getId(), wineUpdated.getId());
        assertEquals(wineOutputDTO.getName(), wineUpdated.getName());
        assertEquals(wineOutputDTO.getPrice(), wineUpdated.getPrice());
        assertEquals(wineOutputDTO.getPurchaseLocation(), wineUpdated.getPurchaseLocation());
        assertEquals(wineOutputDTO.getPurchaseDate(), wineUpdated.getPurchaseDate());
        assertEquals(wineOutputDTO.getWineType(), wineUpdated.getWineType());
        assertEquals(wineOutputDTO.getWineClassification(), wineUpdated.getWineClassification());
        assertEquals(wineOutputDTO.getAlcoholContent(), wineUpdated.getAlcoholContent());
        assertEquals(wineOutputDTO.getVolumeMl(), wineUpdated.getVolumeMl());
        assertEquals(wineOutputDTO.getGrape(), wineUpdated.getGrape());
        assertEquals(wineOutputDTO.getWinery(), wineUpdated.getWinery());
        assertEquals(wineOutputDTO.getServiceTemperature(), wineUpdated.getServiceTemperature());
        assertEquals(wineOutputDTO.getHarvest(), wineUpdated.getHarvest());
        assertEquals(wineOutputDTO.getCountry(), wineUpdated.getCountry());
        assertEquals(wineOutputDTO.getGuardTime(), wineUpdated.getGuardTime());
        assertEquals(wineOutputDTO.getRegion(), wineUpdated.getRegion());
        assertEquals(wineOutputDTO.getMaturation(), wineUpdated.getMaturation());
        assertEquals(wineOutputDTO.getHarmonization(), wineUpdated.getHarmonization());
        verify(wineClient).updateWine(wineOutputDTO.getId().toString(), wineInputDTO);
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao atualizar um vinho")
    void testUpdateWineThrowBadRequestException() {
        when(wineClient.updateWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", wineInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> wineService
                .updateWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", wineInputDTO));

        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(wineClient).updateWine(wineOutputDTO.getId().toString(), wineInputDTO);
    }

    @Test
    @DisplayName("Deve deletar um vinho")
    void testDeleteWine() {
        assertDoesNotThrow(() -> wineService.deleteWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));

        verify(wineClient).deleteWine(wineOutputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao deletar um vinho")
    void testDeleteWineThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(wineClient).deleteWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");

        Exception exception = assertThrows(Exception.class, () -> wineService.deleteWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(wineClient).deleteWine(wineOutputDTO.getId().toString());
    }

    private WineInputDTO createWineInputDTO() {
        return WineInputDTO.builder()
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent(12.5)
                .volumeMl(750)
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature(17.0)
                .harvest(2020)
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, " +
                        "excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

    private WineOutputDTO createWineOutputDTO() {
        return WineOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent(12.5)
                .volumeMl(750)
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature(17.0)
                .harvest(2020)
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, " +
                        "excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

}