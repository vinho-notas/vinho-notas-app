package com.vinhonotas.degustacao.application.services.impl;

import com.vinhonotas.degustacao.application.converters.AromasConverter;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.infraestructure.AromasRepository;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AromasServiceImplTest {

    @InjectMocks
    private AromasServiceImpl aromasService;

    @Mock
    private AromasRepository aromasRepository;
    @Mock
    private AromasConverter aromasConverter;

    private AromasEntity aromasEntity;
    private AromasInputDTO aromasInputDTO;

    @BeforeEach
    void setUp() {
        aromasEntity = createAromasEntity();
        aromasInputDTO = createAromasInputDTO();
    }

    @Test
    @DisplayName("Deve criar um novo registro de Aromas")
    void testCreate() {
        when(aromasConverter.toEntity(aromasInputDTO)).thenReturn(aromasEntity);
        when(aromasRepository.save(aromasEntity)).thenReturn(aromasEntity);
        AromasEntity entity = assertDoesNotThrow(() -> aromasService.create(aromasInputDTO));

        assertNotNull(entity);
        assertEquals(aromasInputDTO.getTastingData(), entity.getTastingData());
        assertEquals(aromasInputDTO.getWineTasted(), entity.getWineTasted());
        assertEquals(aromasInputDTO.getFruity(), EnumConverter.toString(entity.getFruity()));
        assertEquals(aromasInputDTO.getDryFruits(), EnumConverter.toString(entity.getDryFruits()));
        assertEquals(aromasInputDTO.getFlorals(), EnumConverter.toString(entity.getFlorals()));
        assertEquals(aromasInputDTO.getVegetablesAndHerbs(), EnumConverter.toString(entity.getVegetablesAndHerbs()));
        assertEquals(aromasInputDTO.getMinerals(), EnumConverter.toString(entity.getMinerals()));
        assertEquals(aromasInputDTO.getSpices(), EnumConverter.toString(entity.getSpices()));
        assertEquals(aromasInputDTO.getAnimals(), EnumConverter.toString(entity.getAnimals()));
        assertEquals(aromasInputDTO.getEmpireumatics(), EnumConverter.toString(entity.getEmpireumatics()));
        assertEquals(aromasInputDTO.getWood(), EnumConverter.toString(entity.getWood()));
        assertEquals(aromasInputDTO.getChemicals(), EnumConverter.toString(entity.getChemicals()));
        assertEquals(aromasInputDTO.getLacteal(), EnumConverter.toString(entity.getLacteal()));
        assertEquals(aromasInputDTO.getSweets(), EnumConverter.toString(entity.getSweets()));
        verify(aromasRepository).save(aromasEntity);
        verify(aromasConverter).toEntity(aromasInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar criar um novo registro de Aromas")
    void testCreateThrowsBadRequestException() {
        when(aromasRepository.save(aromasEntity)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_AROMAS));
        Exception exception = assertThrows(Exception.class, () -> aromasService.create(aromasInputDTO));

        assertEquals(MessagesConstants.ERROR_WHEN_SAVING_AROMAS, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma lista de Aromas")
    void testGetAll() {
        when(aromasRepository.findAll()).thenReturn(List.of(aromasEntity));
        List<AromasEntity> list = assertDoesNotThrow(() -> aromasService.getAll());

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(aromasEntity.getTastingData(), list.get(0).getTastingData());
        assertEquals(aromasEntity.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(aromasEntity.getFruity(), list.get(0).getFruity());
        assertEquals(aromasEntity.getDryFruits(), list.get(0).getDryFruits());
        assertEquals(aromasEntity.getFlorals(), list.get(0).getFlorals());
        assertEquals(aromasEntity.getVegetablesAndHerbs(), list.get(0).getVegetablesAndHerbs());
        assertEquals(aromasEntity.getMinerals(), list.get(0).getMinerals());
        assertEquals(aromasEntity.getSpices(), list.get(0).getSpices());
        assertEquals(aromasEntity.getAnimals(), list.get(0).getAnimals());
        assertEquals(aromasEntity.getEmpireumatics(), list.get(0).getEmpireumatics());
        assertEquals(aromasEntity.getWood(), list.get(0).getWood());
        assertEquals(aromasEntity.getChemicals(), list.get(0).getChemicals());
        assertEquals(aromasEntity.getLacteal(), list.get(0).getLacteal());
        assertEquals(aromasEntity.getSweets(), list.get(0).getSweets());
        verify(aromasRepository).findAll();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar uma lista de Aromas")
    void testGetAllThrowsBadRequestException() {
        when(aromasRepository.findAll()).thenReturn(List.of());
        Exception exception = assertThrows(Exception.class, () -> aromasService.getAll());

        assertEquals(MessagesConstants.AROMAS_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um registro de Aromas")
    void testGetById() {
        when(aromasRepository.findById(aromasEntity.getId())).thenReturn(Optional.of(aromasEntity));
        AromasEntity entity = assertDoesNotThrow(() -> aromasService.getById(aromasEntity.getId()));

        assertNotNull(entity);
        assertEquals(aromasEntity.getTastingData(), entity.getTastingData());
        assertEquals(aromasEntity.getWineTasted(), entity.getWineTasted());
        assertEquals(aromasEntity.getFruity(), entity.getFruity());
        assertEquals(aromasEntity.getDryFruits(), entity.getDryFruits());
        assertEquals(aromasEntity.getFlorals(), entity.getFlorals());
        assertEquals(aromasEntity.getVegetablesAndHerbs(), entity.getVegetablesAndHerbs());
        assertEquals(aromasEntity.getMinerals(), entity.getMinerals());
        assertEquals(aromasEntity.getSpices(), entity.getSpices());
        assertEquals(aromasEntity.getAnimals(), entity.getAnimals());
        assertEquals(aromasEntity.getEmpireumatics(), entity.getEmpireumatics());
        assertEquals(aromasEntity.getWood(), entity.getWood());
        assertEquals(aromasEntity.getChemicals(), entity.getChemicals());
        assertEquals(aromasEntity.getLacteal(), entity.getLacteal());
        assertEquals(aromasEntity.getSweets(), entity.getSweets());
        verify(aromasRepository).findById(aromasEntity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar um registro de Aromas")
    void testGetByIdThrowsBadRequestException() {
        when(aromasRepository.findById(aromasEntity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> aromasService.getById(aromasEntity.getId()));

        assertEquals(MessagesConstants.AROMAS_NOT_FOUND, exception.getMessage());
    }

    @Test
    @DisplayName("Deve atualizar um registro de Aromas")
    void testUpdate() {
        when(aromasRepository.findById(aromasEntity.getId())).thenReturn(Optional.of(aromasEntity));
        when(aromasConverter.toEntityUpdate(aromasInputDTO, aromasEntity.getId(), aromasEntity)).thenReturn(aromasEntity);
        when(aromasRepository.save(aromasEntity)).thenReturn(aromasEntity);

        aromasEntity.setAnimals(EnumAnimalsType.HUNTING);
        aromasEntity.setSweets(EnumSweetsType.HONEY);

        AromasEntity entityUpdated = assertDoesNotThrow(() -> aromasService.update(aromasEntity.getId(), aromasInputDTO));

        assertNotNull(entityUpdated);
        assertEquals(EnumAnimalsType.HUNTING, entityUpdated.getAnimals());
        assertEquals(EnumSweetsType.HONEY, entityUpdated.getSweets());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar atualizar um registro de Aromas")
    void testUpdateThrowsBadRequestException() {
        when(aromasRepository.findById(aromasEntity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> aromasService.update(aromasEntity.getId(), aromasInputDTO));

        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING_AROMAS, exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar um registro de Aromas")
    void testDelete() {
        when(aromasRepository.findById(aromasEntity.getId())).thenReturn(Optional.of(aromasEntity));

        assertDoesNotThrow(() -> aromasService.delete(aromasEntity.getId()));
        verify(aromasRepository).deleteById(aromasEntity.getId());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar deletar um registro de Aromas")
    void testDeleteThrowsBadRequestException() {
        Exception exception = assertThrows(Exception.class, () -> aromasService.delete(aromasEntity.getId()));

        assertEquals(MessagesConstants.AROMAS_NOT_FOUND, exception.getMessage());
        when(aromasRepository.findById(aromasEntity.getId())).thenReturn(Optional.of(aromasEntity));
        doThrow(BadRequestException.class).when(aromasRepository).deleteById(aromasEntity.getId());

        exception = assertThrows(Exception.class, () -> aromasService.delete(aromasEntity.getId()));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING_AROMAS, exception.getMessage());
    }

    private AromasInputDTO createAromasInputDTO() {
        return AromasInputDTO.builder()
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .fruity(EnumFruityType.RASPBERRY.getCode())
                .dryFruits(EnumDryFruitsType.BRUNETTE.getCode())
                .florals(EnumFloralsType.CLOVE.getCode())
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL.getCode())
                .minerals(EnumMineralsType.EARTH.getCode())
                .spices(EnumSpicesType.INDIAN_CLOVE.getCode())
                .animals(EnumAnimalsType.LEATHER.getCode())
                .empireumatics(EnumEmpireumaticsType.CARAMEL.getCode())
                .wood(EnumWoodType.SAWDUST.getCode())
                .chemicals(EnumChemicalsAndEtherealType.ACETONE.getCode())
                .lacteal(EnumLactealType.BUTTER.getCode())
                .sweets(EnumSweetsType.BULLET.getCode())
                .build();
    }

    private AromasEntity createAromasEntity() {
        return AromasEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .wineTasted("Wine Tasted")
                .fruity(EnumFruityType.RASPBERRY)
                .dryFruits(EnumDryFruitsType.BRUNETTE)
                .florals(EnumFloralsType.CLOVE)
                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL)
                .minerals(EnumMineralsType.EARTH)
                .spices(EnumSpicesType.INDIAN_CLOVE)
                .animals(EnumAnimalsType.LEATHER)
                .empireumatics(EnumEmpireumaticsType.CARAMEL)
                .wood(EnumWoodType.SAWDUST)
                .chemicals(EnumChemicalsAndEtherealType.ACETONE)
                .lacteal(EnumLactealType.BUTTER)
                .sweets(EnumSweetsType.BULLET)
                .build();
    }

}
