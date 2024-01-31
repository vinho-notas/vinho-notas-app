package com.vinhonotas.degustacao.application.converters;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.AromasOutputDTO;
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
    void toEntity() {
        AromasEntity aromas = assertDoesNotThrow(() -> aromasConverter.toEntity(aromasInputDTO));

        assertNotNull(aromas);
        assertEquals(aromasInputDTO.getTastingData(), aromas.getTastingData());
        assertEquals(aromasInputDTO.getWineTasted(), aromas.getWineTasted());
        assertEquals(aromasInputDTO.getFruity(), aromas.getFruity());
        assertEquals(aromasInputDTO.getDryFruits(), aromas.getDryFruits());
        assertEquals(aromasInputDTO.getFlorals(), aromas.getFlorals());
        assertEquals(aromasInputDTO.getVegetablesAndHerbs(), aromas.getVegetablesAndHerbs());
        assertEquals(aromasInputDTO.getMinerals(), aromas.getMinerals());
        assertEquals(aromasInputDTO.getSpices(), aromas.getSpices());
        assertEquals(aromasInputDTO.getAnimals(), aromas.getAnimals());
        assertEquals(aromasInputDTO.getEmpireumatics(), aromas.getEmpireumatics());
        assertEquals(aromasInputDTO.getWood(), aromas.getWood());
        assertEquals(aromasInputDTO.getChemicals(), aromas.getChemicals());
        assertEquals(aromasInputDTO.getLacteal(), aromas.getLacteal());
        assertEquals(aromasInputDTO.getSweets(), aromas.getSweets());
    }

    @Test
    @DisplayName("Deve converter para entidade aromas com alterações")
    void toEntityUpdate() {
        AromasEntity aromas = assertDoesNotThrow(() -> aromasConverter.toEntityUpdate(aromasInputDTO, aromasEntity.getId(), aromasEntity));

        assertNotNull(aromas);
        assertEquals(aromasInputDTO.getTastingData(), aromas.getTastingData());
        assertEquals(aromasInputDTO.getWineTasted(), aromas.getWineTasted());
        assertEquals(aromasInputDTO.getFruity(), aromas.getFruity());
        assertEquals(aromasInputDTO.getDryFruits(), aromas.getDryFruits());
        assertEquals(aromasInputDTO.getFlorals(), aromas.getFlorals());
        assertEquals(aromasInputDTO.getVegetablesAndHerbs(), aromas.getVegetablesAndHerbs());
        assertEquals(aromasInputDTO.getMinerals(), aromas.getMinerals());
        assertEquals(aromasInputDTO.getSpices(), aromas.getSpices());
        assertEquals(aromasInputDTO.getAnimals(), aromas.getAnimals());
        assertEquals(aromasInputDTO.getEmpireumatics(), aromas.getEmpireumatics());
        assertEquals(aromasInputDTO.getWood(), aromas.getWood());
        assertEquals(aromasInputDTO.getChemicals(), aromas.getChemicals());
        assertEquals(aromasInputDTO.getLacteal(), aromas.getLacteal());
        assertEquals(aromasInputDTO.getSweets(), aromas.getSweets());
    }

    @Test
    @DisplayName("Deve converter para DTO de saída")
    void toOutputDTO() {
        AromasOutputDTO outputDTO = assertDoesNotThrow(() -> aromasConverter.toOutputDTO(aromasEntity));

        assertNotNull(outputDTO);
        assertEquals(aromasEntity.getId(), outputDTO.getId());
        assertEquals(aromasEntity.getTastingData(), outputDTO.getTastingData());
        assertEquals(aromasEntity.getWineTasted(), outputDTO.getWineTasted());
        assertEquals(aromasEntity.getFruity(), outputDTO.getFruity());
        assertEquals(aromasEntity.getDryFruits(), outputDTO.getDryFruits());
        assertEquals(aromasEntity.getFlorals(), outputDTO.getFlorals());
        assertEquals(aromasEntity.getVegetablesAndHerbs(), outputDTO.getVegetablesAndHerbs());
        assertEquals(aromasEntity.getMinerals(), outputDTO.getMinerals());
        assertEquals(aromasEntity.getSpices(), outputDTO.getSpices());
        assertEquals(aromasEntity.getAnimals(), outputDTO.getAnimals());
        assertEquals(aromasEntity.getEmpireumatics(), outputDTO.getEmpireumatics());
        assertEquals(aromasEntity.getWood(), outputDTO.getWood());
        assertEquals(aromasEntity.getChemicals(), outputDTO.getChemicals());
        assertEquals(aromasEntity.getLacteal(), outputDTO.getLacteal());
        assertEquals(aromasEntity.getSweets(), outputDTO.getSweets());
    }

    @Test
    @DisplayName("Deve converter para lista de DTO de saída")
    void toOutputDTOList() {
        List<AromasOutputDTO> list = assertDoesNotThrow(() -> aromasConverter.toOutputDTOList(List.of(aromasEntity)));

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(aromasEntity.getId(), list.get(0).getId());
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
    }

    @Test
    @DisplayName("Deve converter para DTO de saída com alterações")
    void toOutputDTOUpdate() {
        AromasOutputDTO outputDTO = assertDoesNotThrow(() -> aromasConverter.toOutputDTOUpdate(aromasEntity, aromasEntity.getId(), aromasOutputDTO));

        assertNotNull(outputDTO);
        assertEquals(aromasEntity.getId(), outputDTO.getId());
        assertEquals(aromasEntity.getTastingData(), outputDTO.getTastingData());
        assertEquals(aromasEntity.getWineTasted(), outputDTO.getWineTasted());
        assertEquals(aromasEntity.getFruity(), outputDTO.getFruity());
        assertEquals(aromasEntity.getDryFruits(), outputDTO.getDryFruits());
        assertEquals(aromasEntity.getFlorals(), outputDTO.getFlorals());
        assertEquals(aromasEntity.getVegetablesAndHerbs(), outputDTO.getVegetablesAndHerbs());
        assertEquals(aromasEntity.getMinerals(), outputDTO.getMinerals());
        assertEquals(aromasEntity.getSpices(), outputDTO.getSpices());
        assertEquals(aromasEntity.getAnimals(), outputDTO.getAnimals());
        assertEquals(aromasEntity.getEmpireumatics(), outputDTO.getEmpireumatics());
        assertEquals(aromasEntity.getWood(), outputDTO.getWood());
        assertEquals(aromasEntity.getChemicals(), outputDTO.getChemicals());
        assertEquals(aromasEntity.getLacteal(), outputDTO.getLacteal());
        assertEquals(aromasEntity.getSweets(), outputDTO.getSweets());
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

    private AromasInputDTO createAromasInputDTO() {
        return AromasInputDTO.builder()
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