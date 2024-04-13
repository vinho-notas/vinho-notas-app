//package com.vinhonotas.bff.application.services.degustacao.impl;
//
//import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
//import com.vinhonotas.bff.client.degustacao.AromasClient;
//import com.vinhonotas.bff.domain.enums.*;
//import com.vinhonotas.bff.utils.MessagesConstants;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class AromasServiceImplTest {
//
//    @InjectMocks
//    private AromasServiceImpl aromasService;
//
//    @Mock
//    private AromasClient aromasClient;
//
//    private AromasOutputDTO aromasOutputDTO;
//    private AromasInputDTO aromasInputDTO;
//
//    @BeforeEach
//    void setUp() {
//        aromasOutputDTO = createAromasOutputDTO();
//        aromasInputDTO = createAromasInputDTO();
//    }
//
//    @Test
//    @DisplayName("Deve criar um novo registro de Aromas")
//    void testCreate() {
//        when(aromasClient.createAromas(aromasInputDTO)).thenReturn(aromasOutputDTO);
//
//        AromasOutputDTO response = assertDoesNotThrow(() -> aromasService.createAromas(aromasInputDTO));
//
//        assertNotNull(response);
//        assertEquals(aromasOutputDTO.getTastingData(), response.getTastingData());
//        assertEquals(aromasOutputDTO.getWineTasted(), response.getWineTasted());
//        assertEquals(aromasOutputDTO.getFruity(), response.getFruity());
//        assertEquals(aromasOutputDTO.getDryFruits(), response.getDryFruits());
//        assertEquals(aromasOutputDTO.getFlorals(), response.getFlorals());
//        assertEquals(aromasOutputDTO.getVegetablesAndHerbs(), response.getVegetablesAndHerbs());
//        assertEquals(aromasOutputDTO.getMinerals(), response.getMinerals());
//        assertEquals(aromasOutputDTO.getSpices(), response.getSpices());
//        assertEquals(aromasOutputDTO.getAnimals(), response.getAnimals());
//        assertEquals(aromasOutputDTO.getEmpireumatics(), response.getEmpireumatics());
//        assertEquals(aromasOutputDTO.getWood(), response.getWood());
//        assertEquals(aromasOutputDTO.getChemicals(), response.getChemicals());
//        assertEquals(aromasOutputDTO.getLacteal(), response.getLacteal());
//        assertEquals(aromasOutputDTO.getSweets(), response.getSweets());
//        verify(aromasClient).createAromas(aromasInputDTO);
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao tentar criar um novo registro de Aromas")
//    void testCreateThrowsBadRequestException() {
//        when(aromasClient.createAromas(aromasInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));
//
//        Exception exception = assertThrows(Exception.class, () -> aromasService.createAromas(aromasInputDTO));
//
//        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma lista de Aromas")
//    void testGetAll() {
//        when(aromasClient.getAllAromas()).thenReturn(List.of(aromasOutputDTO));
//
//        List<AromasOutputDTO> list = assertDoesNotThrow(() -> aromasService.getAllAromas());
//
//        assertNotNull(list);
//        assertFalse(list.isEmpty());
//        assertEquals(1, list.size());
//        assertEquals(aromasOutputDTO.getTastingData(), list.get(0).getTastingData());
//        assertEquals(aromasOutputDTO.getWineTasted(), list.get(0).getWineTasted());
//        assertEquals(aromasOutputDTO.getFruity(), list.get(0).getFruity());
//        assertEquals(aromasOutputDTO.getDryFruits(), list.get(0).getDryFruits());
//        assertEquals(aromasOutputDTO.getFlorals(), list.get(0).getFlorals());
//        assertEquals(aromasOutputDTO.getVegetablesAndHerbs(), list.get(0).getVegetablesAndHerbs());
//        assertEquals(aromasOutputDTO.getMinerals(), list.get(0).getMinerals());
//        assertEquals(aromasOutputDTO.getSpices(), list.get(0).getSpices());
//        assertEquals(aromasOutputDTO.getAnimals(), list.get(0).getAnimals());
//        assertEquals(aromasOutputDTO.getEmpireumatics(), list.get(0).getEmpireumatics());
//        assertEquals(aromasOutputDTO.getWood(), list.get(0).getWood());
//        assertEquals(aromasOutputDTO.getChemicals(), list.get(0).getChemicals());
//        assertEquals(aromasOutputDTO.getLacteal(), list.get(0).getLacteal());
//        assertEquals(aromasOutputDTO.getSweets(), list.get(0).getSweets());
//        verify(aromasClient).getAllAromas();
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao tentar retornar uma lista de Aromas")
//    void testGetAllThrowsBadRequestException() {
//        when(aromasClient.getAllAromas()).thenReturn(List.of());
//        Exception exception = assertThrows(Exception.class, () -> aromasService.getAllAromas());
//
//        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve retornar um registro de Aromas")
//    void testGetById() {
//        when(aromasClient.getAromasById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenReturn(aromasOutputDTO);
//
//        AromasOutputDTO entity = assertDoesNotThrow(() -> aromasService.getAromasById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
//
//        assertNotNull(entity);
//        assertEquals(aromasOutputDTO.getTastingData(), entity.getTastingData());
//        assertEquals(aromasOutputDTO.getWineTasted(), entity.getWineTasted());
//        assertEquals(aromasOutputDTO.getFruity(), entity.getFruity());
//        assertEquals(aromasOutputDTO.getDryFruits(), entity.getDryFruits());
//        assertEquals(aromasOutputDTO.getFlorals(), entity.getFlorals());
//        assertEquals(aromasOutputDTO.getVegetablesAndHerbs(), entity.getVegetablesAndHerbs());
//        assertEquals(aromasOutputDTO.getMinerals(), entity.getMinerals());
//        assertEquals(aromasOutputDTO.getSpices(), entity.getSpices());
//        assertEquals(aromasOutputDTO.getAnimals(), entity.getAnimals());
//        assertEquals(aromasOutputDTO.getEmpireumatics(), entity.getEmpireumatics());
//        assertEquals(aromasOutputDTO.getWood(), entity.getWood());
//        assertEquals(aromasOutputDTO.getChemicals(), entity.getChemicals());
//        assertEquals(aromasOutputDTO.getLacteal(), entity.getLacteal());
//        assertEquals(aromasOutputDTO.getSweets(), entity.getSweets());
//        verify(aromasClient).getAromasById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao tentar retornar um registro de Aromas")
//    void testGetByIdThrowsBadRequestException() {
//        when(aromasClient.getAromasById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e")).thenReturn(null);
//        Exception exception = assertThrows(Exception.class, () -> aromasService.getAromasById("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
//
//        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar um registro de Aromas")
//    void testUpdate() {
//      when(aromasClient.updateAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", aromasInputDTO)).thenReturn(aromasOutputDTO);
//
//      AromasOutputDTO aromasUpdated = assertDoesNotThrow(() -> aromasService.updateAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", aromasInputDTO));
//
//        assertNotNull(aromasUpdated);
//        assertEquals(aromasOutputDTO.getTastingData(), aromasUpdated.getTastingData());
//        assertEquals(aromasOutputDTO.getWineTasted(), aromasUpdated.getWineTasted());
//        assertEquals(aromasOutputDTO.getFruity(), aromasUpdated.getFruity());
//        assertEquals(aromasOutputDTO.getDryFruits(), aromasUpdated.getDryFruits());
//        assertEquals(aromasOutputDTO.getFlorals(), aromasUpdated.getFlorals());
//        assertEquals(aromasOutputDTO.getVegetablesAndHerbs(), aromasUpdated.getVegetablesAndHerbs());
//        assertEquals(aromasOutputDTO.getMinerals(), aromasUpdated.getMinerals());
//        assertEquals(aromasOutputDTO.getSpices(), aromasUpdated.getSpices());
//        assertEquals(aromasOutputDTO.getAnimals(), aromasUpdated.getAnimals());
//        assertEquals(aromasOutputDTO.getEmpireumatics(), aromasUpdated.getEmpireumatics());
//        assertEquals(aromasOutputDTO.getWood(), aromasUpdated.getWood());
//        assertEquals(aromasOutputDTO.getChemicals(), aromasUpdated.getChemicals());
//        assertEquals(aromasOutputDTO.getLacteal(), aromasUpdated.getLacteal());
//        assertEquals(aromasOutputDTO.getSweets(), aromasUpdated.getSweets());
//        verify(aromasClient).updateAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", aromasInputDTO);
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao tentar atualizar um registro de Aromas")
//    void testUpdateThrowsBadRequestException() {
//        when(aromasClient.updateAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", aromasInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));
//        Exception exception = assertThrows(Exception.class, () -> aromasService.updateAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e", aromasInputDTO));
//
//        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve deletar um registro de Aromas")
//    void testDelete() {
//        assertDoesNotThrow(() -> aromasService.deleteAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
//        verify(aromasClient).deleteAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
//    }
//
//    @Test
//    @DisplayName("Deve lançar BadRequestException ao tentar deletar um registro de Aromas")
//    void testDeleteThrowsBadRequestException() {
//        doThrow(BadRequestException.class).when(aromasClient).deleteAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e");
//
//        Exception exception = assertThrows(Exception.class, () -> aromasService.deleteAromas("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"));
//        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
//    }
//
//    private AromasInputDTO createAromasInputDTO() {
//        return AromasInputDTO.builder()
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .fruity(EnumFruityType.RASPBERRY.getCode())
//                .dryFruits(EnumDryFruitsType.BRUNETTE.getCode())
//                .florals(EnumFloralsType.CLOVE.getCode())
//                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL.getCode())
//                .minerals(EnumMineralsType.EARTH.getCode())
//                .spices(EnumSpicesType.INDIAN_CLOVE.getCode())
//                .animals(EnumAnimalsType.LEATHER.getCode())
//                .empireumatics(EnumEmpireumaticsType.CARAMEL.getCode())
//                .wood(EnumWoodType.SAWDUST.getCode())
//                .chemicals(EnumChemicalsAndEtherealType.ACETONE.getCode())
//                .lacteal(EnumLactealType.BUTTER.getCode())
//                .sweets(EnumSweetsType.BULLET.getCode())
//                .build();
//    }
//
//    private AromasOutputDTO createAromasOutputDTO() {
//        return AromasOutputDTO.builder()
//                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
//                .tastingData(LocalDate.now())
//                .wineTasted("Wine Tasted")
//                .fruity(EnumFruityType.RASPBERRY.getCode())
//                .dryFruits(EnumDryFruitsType.BRUNETTE.getCode())
//                .florals(EnumFloralsType.CLOVE.getCode())
//                .vegetablesAndHerbs(EnumVegetablesAndHerbsType.FENNEL.getCode())
//                .minerals(EnumMineralsType.EARTH.getCode())
//                .spices(EnumSpicesType.INDIAN_CLOVE.getCode())
//                .animals(EnumAnimalsType.LEATHER.getCode())
//                .empireumatics(EnumEmpireumaticsType.CARAMEL.getCode())
//                .wood(EnumWoodType.SAWDUST.getCode())
//                .chemicals(EnumChemicalsAndEtherealType.ACETONE.getCode())
//                .lacteal(EnumLactealType.BUTTER.getCode())
//                .sweets(EnumSweetsType.BULLET.getCode())
//                .build();
//    }
//
//}