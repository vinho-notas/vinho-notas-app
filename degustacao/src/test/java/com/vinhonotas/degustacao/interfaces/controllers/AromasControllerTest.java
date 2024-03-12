package com.vinhonotas.degustacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.degustacao.application.converters.AromasConverter;
import com.vinhonotas.degustacao.application.services.AromasService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import com.vinhonotas.degustacao.domain.enums.*;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.AromasInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.AromasOutputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AromasController.class)
class AromasControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AromasService aromasService;
    @MockBean
    private AromasConverter aromasConverter;

    private AromasEntity aromasEntity;
    private AromasInputDTO aromasInputDTO;
    private AromasOutputDTO aromasOutputDTO;

    @BeforeEach
    void setUp() {
        aromasEntity = createAromasEntity();
        aromasInputDTO = createAromasInputDTO();
        aromasOutputDTO = createAromasOutputDTO();
    }

    @Test
    @DisplayName("Deve criar um novo aroma")
    void testCreateAromas() throws Exception {
        when(aromasService.create(aromasInputDTO)).thenReturn(aromasEntity);
        when(aromasConverter.toOutputDTO(aromasEntity)).thenReturn(aromasOutputDTO);

        mockMvc.perform(post("/api/v1/aromas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aromasInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(aromasOutputDTO)));
    }

    @Test
    @DisplayName("Deve retornar um erro ao criar um aroma")
    void testCreateAromasError() throws Exception {
        when(aromasService.create(aromasInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_AROMAS));

        mockMvc.perform(post("/api/v1/aromas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aromasInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todos os aromas cadastrados")
    void testGetAllAromas() throws Exception {
        List<AromasOutputDTO> list = List.of(aromasOutputDTO);
        when(aromasService.getAll()).thenReturn(List.of(aromasEntity));
        when(aromasConverter.toOutputDTOList(List.of(aromasEntity))).thenReturn(list);

        mockMvc.perform(get("/api/v1/aromas")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    @DisplayName("Deve retornar uma erro ao buscar todos os aromas")
    void testGetAllAromasError() throws Exception {
        when(aromasService.getAll()).thenThrow(new BadRequestException(MessagesConstants.AROMAS_NOT_FOUND));

        mockMvc.perform(get("/api/v1/aromas")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um aroma pelo id")
    void testGetAromasById() throws Exception {
        when(aromasService.getById(aromasEntity.getId())).thenReturn(aromasEntity);
        when(aromasConverter.toOutputDTO(aromasEntity)).thenReturn(aromasOutputDTO);

        mockMvc.perform(get("/api/v1/aromas/" + aromasEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(aromasOutputDTO)));
    }

    @Test
    @DisplayName("Deve retornar um erro ao buscar um aroma pelo id")
    void testGetAromasByIdError() throws Exception {
        when(aromasService.getById(aromasEntity.getId())).thenThrow(new BadRequestException(MessagesConstants.AROMAS_NOT_FOUND));

        mockMvc.perform(get("/api/v1/aromas/" + aromasEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um aroma pelo id")
    void testUpdateAromas() throws Exception {
        when(aromasService.update(aromasEntity.getId(), aromasInputDTO)).thenReturn(aromasEntity);
        when(aromasConverter.toOutputDTO(aromasEntity)).thenReturn(aromasOutputDTO);
        aromasOutputDTO.setFruity(EnumFruityType.KIWI);

        mockMvc.perform(put("/api/v1/aromas/" + aromasEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aromasInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(aromasOutputDTO)))
                .andExpect(content().json("{'fruity':'KIWI'}"));
    }

    @Test
    @DisplayName("Deve retornar um erro ao atualizar um aroma pelo id")
    void testUpdateAromasError() throws Exception {
        when(aromasService.update(aromasEntity.getId(), aromasInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_AROMAS));

        mockMvc.perform(put("/api/v1/aromas/" + aromasEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aromasInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar um aroma pelo id")
    void testDeleteAromas() throws Exception {
        mockMvc.perform(delete("/api/v1/aromas/" + aromasEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar um erro ao deletar um aroma pelo id")
    void testDeleteAromasError() throws Exception {
        doThrow(BadRequestException.class).when(aromasService).delete(aromasEntity.getId());

        mockMvc.perform(delete("/api/v1/aromas/" + aromasEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
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