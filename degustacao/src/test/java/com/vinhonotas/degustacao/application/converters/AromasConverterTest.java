package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.AromasOutputDTO;
import com.vinhonotas.degustacao.utils.EnumConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AromasConverterTest {

    @InjectMocks
    private AromasConverter aromasConverter;

    private AromasInputDTO aromasInputDTO;
    private AromasOutputDTO aromasOutputDTO;
    private AromasEntity aromasEntity;

    @BeforeEach
    void setUp() {
        aromasInputDTO = createAromasInputDTO();
        aromasOutputDTO = createAromasOutputDTO();
        aromasEntity = createAromasEntity();
    }

    @Test
    @DisplayName("Deve converter para entidade aromas")
    void testToEntity() {
        AromasEntity aromas = assertDoesNotThrow(() -> aromasConverter.toEntity(aromasInputDTO));

        assertNotNull(aromas);
        assertEquals(aromasInputDTO.getTastingData(), aromas.getTastingData());
        assertEquals(aromasInputDTO.getWineTasted(), aromas.getWineTasted());
        assertEquals(aromasInputDTO.getFruity(), EnumConverter.toString(aromas.getFruity()));
        assertEquals(aromasInputDTO.getDryFruits(), EnumConverter.toString(aromas.getDryFruits()));
        assertEquals(aromasInputDTO.getFlorals(), EnumConverter.toString(aromas.getFlorals()));
        assertEquals(aromasInputDTO.getVegetablesAndHerbs(), EnumConverter.toString(aromas.getVegetablesAndHerbs()));
        assertEquals(aromasInputDTO.getMinerals(), EnumConverter.toString(aromas.getMinerals()));
        assertEquals(aromasInputDTO.getSpices(), EnumConverter.toString(aromas.getSpices()));
        assertEquals(aromasInputDTO.getAnimals(), EnumConverter.toString(aromas.getAnimals()));
        assertEquals(aromasInputDTO.getEmpireumatics(), EnumConverter.toString(aromas.getEmpireumatics()));
        assertEquals(aromasInputDTO.getWood(), EnumConverter.toString(aromas.getWood()));
        assertEquals(aromasInputDTO.getChemicals(), EnumConverter.toString(aromas.getChemicals()));
        assertEquals(aromasInputDTO.getLacteal(), EnumConverter.toString(aromas.getLacteal()));
        assertEquals(aromasInputDTO.getSweets(), EnumConverter.toString(aromas.getSweets()));
    }

    @Test
    @DisplayName("Deve converter para entidade aromas com alterações")
    void testToEntityUpdate() {
        AromasEntity aromas = assertDoesNotThrow(() -> aromasConverter.toEntityUpdate(aromasInputDTO, aromasEntity.getId(), aromasEntity));

        assertNotNull(aromas);
        assertEquals(aromasInputDTO.getTastingData(), aromas.getTastingData());
        assertEquals(aromasInputDTO.getWineTasted(), aromas.getWineTasted());
        assertEquals(aromasInputDTO.getFruity(), EnumConverter.toString(aromas.getFruity()));
        assertEquals(aromasInputDTO.getDryFruits(), EnumConverter.toString(aromas.getDryFruits()));
        assertEquals(aromasInputDTO.getFlorals(), EnumConverter.toString(aromas.getFlorals()));
        assertEquals(aromasInputDTO.getVegetablesAndHerbs(), EnumConverter.toString(aromas.getVegetablesAndHerbs()));
        assertEquals(aromasInputDTO.getMinerals(), EnumConverter.toString(aromas.getMinerals()));
        assertEquals(aromasInputDTO.getSpices(), EnumConverter.toString(aromas.getSpices()));
        assertEquals(aromasInputDTO.getAnimals(), EnumConverter.toString(aromas.getAnimals()));
        assertEquals(aromasInputDTO.getEmpireumatics(), EnumConverter.toString(aromas.getEmpireumatics()));
        assertEquals(aromasInputDTO.getWood(), EnumConverter.toString(aromas.getWood()));
        assertEquals(aromasInputDTO.getChemicals(), EnumConverter.toString(aromas.getChemicals()));
        assertEquals(aromasInputDTO.getLacteal(), EnumConverter.toString(aromas.getLacteal()));
        assertEquals(aromasInputDTO.getSweets(), EnumConverter.toString(aromas.getSweets()));
    }

    @Test
    @DisplayName("Deve converter para DTO de saída")
    void testToOutputDTO() {
        AromasOutputDTO outputDTO = assertDoesNotThrow(() -> aromasConverter.toOutputDTO(aromasEntity));

        assertNotNull(outputDTO);
        assertEquals(aromasEntity.getId(), outputDTO.getId());
        assertEquals(aromasEntity.getTastingData(), outputDTO.getTastingData());
        assertEquals(aromasEntity.getWineTasted(), outputDTO.getWineTasted());
        assertEquals(aromasEntity.getFruity().getCode(), outputDTO.getFruity());
        assertEquals(aromasEntity.getDryFruits().getCode(), outputDTO.getDryFruits());
        assertEquals(aromasEntity.getFlorals().getCode(), outputDTO.getFlorals());
        assertEquals(aromasEntity.getVegetablesAndHerbs().getCode(), outputDTO.getVegetablesAndHerbs());
        assertEquals(aromasEntity.getMinerals().getCode(), outputDTO.getMinerals());
        assertEquals(aromasEntity.getSpices().getCode(), outputDTO.getSpices());
        assertEquals(aromasEntity.getAnimals().getCode(), outputDTO.getAnimals());
        assertEquals(aromasEntity.getEmpireumatics().getCode(), outputDTO.getEmpireumatics());
        assertEquals(aromasEntity.getWood().getCode(), outputDTO.getWood());
        assertEquals(aromasEntity.getChemicals().getCode(), outputDTO.getChemicals());
        assertEquals(aromasEntity.getLacteal().getCode(), outputDTO.getLacteal());
        assertEquals(aromasEntity.getSweets().getCode(), outputDTO.getSweets());
    }

    @Test
    @DisplayName("Deve converter para lista de DTO de saída")
    void testToOutputDTOList() {
        List<AromasOutputDTO> list = assertDoesNotThrow(() -> aromasConverter.toOutputDTOList(List.of(aromasEntity)));

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(aromasEntity.getId(), list.get(0).getId());
        assertEquals(aromasEntity.getTastingData(), list.get(0).getTastingData());
        assertEquals(aromasEntity.getWineTasted(), list.get(0).getWineTasted());
        assertEquals(aromasEntity.getFruity().getCode(), list.get(0).getFruity());
        assertEquals(aromasEntity.getDryFruits().getCode(), list.get(0).getDryFruits());
        assertEquals(aromasEntity.getFlorals().getCode(), list.get(0).getFlorals());
        assertEquals(aromasEntity.getVegetablesAndHerbs().getCode(), list.get(0).getVegetablesAndHerbs());
        assertEquals(aromasEntity.getMinerals().getCode(), list.get(0).getMinerals());
        assertEquals(aromasEntity.getSpices().getCode(), list.get(0).getSpices());
        assertEquals(aromasEntity.getAnimals().getCode(), list.get(0).getAnimals());
        assertEquals(aromasEntity.getEmpireumatics().getCode(), list.get(0).getEmpireumatics());
        assertEquals(aromasEntity.getWood().getCode(), list.get(0).getWood());
        assertEquals(aromasEntity.getChemicals().getCode(), list.get(0).getChemicals());
        assertEquals(aromasEntity.getLacteal().getCode(), list.get(0).getLacteal());
        assertEquals(aromasEntity.getSweets().getCode(), list.get(0).getSweets());
    }

    @Test
    @DisplayName("Deve converter para DTO de saída com alterações")
    void testToOutputDTOUpdate() {
        AromasOutputDTO outputDTO = assertDoesNotThrow(() -> aromasConverter.toOutputDTOUpdate(aromasEntity, aromasEntity.getId(), aromasOutputDTO));

        assertNotNull(outputDTO);
        assertEquals(aromasEntity.getId(), outputDTO.getId());
        assertEquals(aromasEntity.getTastingData(), outputDTO.getTastingData());
        assertEquals(aromasEntity.getWineTasted(), outputDTO.getWineTasted());
        assertEquals(aromasEntity.getFruity().getCode(), outputDTO.getFruity());
        assertEquals(aromasEntity.getDryFruits().getCode(), outputDTO.getDryFruits());
        assertEquals(aromasEntity.getFlorals().getCode(), outputDTO.getFlorals());
        assertEquals(aromasEntity.getVegetablesAndHerbs().getCode(), outputDTO.getVegetablesAndHerbs());
        assertEquals(aromasEntity.getMinerals().getCode(), outputDTO.getMinerals());
        assertEquals(aromasEntity.getSpices().getCode(), outputDTO.getSpices());
        assertEquals(aromasEntity.getAnimals().getCode(), outputDTO.getAnimals());
        assertEquals(aromasEntity.getEmpireumatics().getCode(), outputDTO.getEmpireumatics());
        assertEquals(aromasEntity.getWood().getCode(), outputDTO.getWood());
        assertEquals(aromasEntity.getChemicals().getCode(), outputDTO.getChemicals());
        assertEquals(aromasEntity.getLacteal().getCode(), outputDTO.getLacteal());
        assertEquals(aromasEntity.getSweets().getCode(), outputDTO.getSweets());
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

    private AromasOutputDTO createAromasOutputDTO() {
        return AromasOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
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
}