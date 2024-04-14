package com.vinhonotas.bff.application.services.degustacao.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.degustacao.TastingCardClient;
import com.vinhonotas.bff.domain.enums.*;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TastingCardServiceImplTest {

    @InjectMocks
    private TastingCardServiceImpl service;

    @Mock
    private TastingCardClient client;

    private TastingCardInputDTO inputDTO;
    private TastingCardOutputDTO outputDTO;

    @BeforeEach
    void setUp() {
        inputDTO = createTastingCardInputDTO();
        outputDTO = createTastingCardOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma ficha de degustação")
    void testCreateTastingCard() {
        when(client.createTastingCard(inputDTO)).thenReturn(outputDTO);

        TastingCardOutputDTO result = assertDoesNotThrow(() -> service.createTastingCard(inputDTO));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getHarvest(), result.getHarvest());
        assertEquals(outputDTO.getGrapes(), result.getGrapes());
        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getRegion(), result.getRegion());
        assertEquals(outputDTO.getOpinion(), result.getOpinion());
        assertEquals(outputDTO.getPointScale(), result.getPointScale());
        verify(client).createTastingCard(inputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar criar uma ficha de degustação")
    void testCreateTastingCardThrowBadRequestException() {
        when(client.createTastingCard(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.createTastingCard(inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(client).createTastingCard(inputDTO);
    }

    @Test
    @DisplayName("Deve listar todas as fichas de degustação")
    void testGetAllTastingCards() {
        when(client.getAllTastingCards()).thenReturn(List.of(outputDTO));

        List<TastingCardOutputDTO> result = assertDoesNotThrow(() -> service.getAllTastingCards());
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(outputDTO.getId(), result.get(0).getId());
        assertEquals(outputDTO.getTastingData(), result.get(0).getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.get(0).getWineTasted());
        assertEquals(outputDTO.getHarvest(), result.get(0).getHarvest());
        assertEquals(outputDTO.getGrapes(), result.get(0).getGrapes());
        assertEquals(outputDTO.getCountry(), result.get(0).getCountry());
        assertEquals(outputDTO.getRegion(), result.get(0).getRegion());
        assertEquals(outputDTO.getOpinion(), result.get(0).getOpinion());
        assertEquals(outputDTO.getPointScale(), result.get(0).getPointScale());
        verify(client).getAllTastingCards();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar listar todas as fichas de degustação")
    void testGetAllTastingCardsThrowBadRequestException() {
        when(client.getAllTastingCards()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.getAllTastingCards());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(client).getAllTastingCards();
    }

    @Test
    @DisplayName("Deve buscar uma ficha de degustação pelo id")
    void testGetTastingCardById() {
        when(client.getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenReturn(outputDTO);

        TastingCardOutputDTO result = assertDoesNotThrow(() -> service.getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getHarvest(), result.getHarvest());
        assertEquals(outputDTO.getGrapes(), result.getGrapes());
        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getRegion(), result.getRegion());
        assertEquals(outputDTO.getOpinion(), result.getOpinion());
        assertEquals(outputDTO.getPointScale(), result.getPointScale());
        verify(client).getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar buscar uma ficha de degustação pelo id")
    void testGetTastingCardByIdThrowBadRequestException() {
        when(client.getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(client).getTastingCardById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve atualizar uma ficha de degustação pelo id")
    void testUpdateTastingCard() {
        when(client.updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO)).thenReturn(outputDTO);

        TastingCardOutputDTO result = assertDoesNotThrow(() -> service.updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO));
        assertNotNull(result);
        assertEquals(outputDTO.getId(), result.getId());
        assertEquals(outputDTO.getTastingData(), result.getTastingData());
        assertEquals(outputDTO.getWineTasted(), result.getWineTasted());
        assertEquals(outputDTO.getHarvest(), result.getHarvest());
        assertEquals(outputDTO.getGrapes(), result.getGrapes());
        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getRegion(), result.getRegion());
        assertEquals(outputDTO.getOpinion(), result.getOpinion());
        assertEquals(outputDTO.getPointScale(), result.getPointScale());
        verify(client).updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar atualizar uma ficha de degustação pelo id")
    void testUpdateTastingCardThrowBadRequestException() {
        when(client.updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(client).updateTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", inputDTO);
    }

    @Test
    @DisplayName("Deve deletar uma ficha de degustação pelo id")
    void testDeleteTastingCard() {
        when(client.getTastingCardById(anyString())).thenReturn(outputDTO);;
        assertDoesNotThrow(() -> service.deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        verify(client).deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar deletar uma ficha de degustação pelo id")
    void testDeleteTastingCardThrowBadRequestException() {
        when(client.getTastingCardById(anyString())).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(client, times(0)).deleteTastingCard("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
    }

    private TastingCardInputDTO createTastingCardInputDTO() {
        return TastingCardInputDTO.builder()
                .wineTasted("Wine Tasted")
                .tastingData(LocalDate.now())
                .harvest("2021")
                .grapes("Grapes")
                .country("Country")
                .region("Region")
                .clarity(EnumClarityType.CLEAR.getCode())
                .brightness(EnumBrightnessType.BRIGHT.getCode())
                .viscosity(EnumViscosityType.VISCOUS.getCode())
                .colorRed(EnumRedColorType.RUBY.getCode())
                .colorWhite(EnumWhiteColorType.GOLDEN.getCode())
                .colorRose(EnumRoseColorType.BROWN.getCode())
                .visualInspectionClassification(EnumClassificationType.LITTLE.getCode())
                .intensity(EnumIntensityType.INTENSE.getCode())
                .olfactoryInspectionPersistence(EnumPersistenceType.PERSISTENT.getCode())
                .quality(EnumQualityType.COMMON.getCode())
                .fruity(EnumFruityType.BANANA.getCode())
                .dryFruits(EnumDryFruitsType.ALMODN.getCode())
                .florals(EnumFloralsType.ACACIA.getCode())
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL.getCode())
                .minerals(EnumMineralsType.CHALK.getCode())
                .spices(EnumSpicesType.ANISE.getCode())
                .animals(EnumAnimalsType.HUNTING.getCode())
                .empireumatics(EnumEmpireumaticsType.CARAMEL.getCode())
                .wood(EnumWoodType.SAWDUST.getCode())
                .chemicals(EnumChemicalsAndEtherealType.ACETONE.getCode())
                .lacteal(EnumLactealType.BUTTER.getCode())
                .sweets(EnumSweetsType.BULLET.getCode())
                .olfactoryInspectionClassification(EnumClassificationType.EXCELLENT.getCode())
                .body(EnumBodyType.LITTLE_BODY.getCode())
                .sweetness(EnumSweetnessType.SWEET.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .gustatoryInspectionPersistence(EnumPersistenceType.PERSISTENT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.TYPICAL.getCode())
                .gustatoryInspectionClassification(EnumClassificationType.LITTLE.getCode())
                .opinion("Opinion")
                .pointScale(EnumPointScale.VERYGOOD.getCode())
                .dthreg(LocalDateTime.now())
                .userreg("User Reg")
                .build();
    }

    private TastingCardOutputDTO createTastingCardOutputDTO() {
        return TastingCardOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .wineTasted("Wine Tasted")
                .tastingData(LocalDate.now())
                .harvest("2021")
                .grapes("Grapes")
                .country("Country")
                .region("Region")
                .clarity(EnumClarityType.CLEAR.getCode())
                .brightness(EnumBrightnessType.BRIGHT.getCode())
                .viscosity(EnumViscosityType.VISCOUS.getCode())
                .colorRed(EnumRedColorType.RUBY.getCode())
                .colorWhite(EnumWhiteColorType.GOLDEN.getCode())
                .colorRose(EnumRoseColorType.BROWN.getCode())
                .visualInspectionClassification(EnumClassificationType.LITTLE.getCode())
                .intensity(EnumIntensityType.INTENSE.getCode())
                .olfactoryInspectionPersistence(EnumPersistenceType.PERSISTENT.getCode())
                .quality(EnumQualityType.COMMON.getCode())
                .fruity(EnumFruityType.BANANA.getCode())
                .dryFruits(EnumDryFruitsType.ALMODN.getCode())
                .florals(EnumFloralsType.ACACIA.getCode())
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL.getCode())
                .minerals(EnumMineralsType.CHALK.getCode())
                .spices(EnumSpicesType.ANISE.getCode())
                .animals(EnumAnimalsType.HUNTING.getCode())
                .empireumatics(EnumEmpireumaticsType.CARAMEL.getCode())
                .wood(EnumWoodType.SAWDUST.getCode())
                .chemicals(EnumChemicalsAndEtherealType.ACETONE.getCode())
                .lacteal(EnumLactealType.BUTTER.getCode())
                .sweets(EnumSweetsType.BULLET.getCode())
                .olfactoryInspectionClassification(EnumClassificationType.EXCELLENT.getCode())
                .body(EnumBodyType.LITTLE_BODY.getCode())
                .sweetness(EnumSweetnessType.SWEET.getCode())
                .tannin(EnumTanninType.LITTLE_TANIC.getCode())
                .acidity(EnumAcidityType.LITTLE_ACID.getCode())
                .alcohol(EnumAlcoholType.LOW.getCode())
                .gustatoryInspectionPersistence(EnumPersistenceType.PERSISTENT.getCode())
                .maturity(EnumMaturityType.MATURE.getCode())
                .typicality(EnumTypicalityType.TYPICAL.getCode())
                .gustatoryInspectionClassification(EnumClassificationType.LITTLE.getCode())
                .opinion("Opinion")
                .pointScale(EnumPointScale.VERYGOOD.getCode())
                .build();
    }

}