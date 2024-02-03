package com.vinhonotas.degustacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.degustacao.application.converters.TastingConverter;
import com.vinhonotas.degustacao.application.services.TastingService;
import com.vinhonotas.degustacao.application.services.exceptions.BadRequestException;
import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import com.vinhonotas.degustacao.domain.enums.EnumTastingType;
import com.vinhonotas.degustacao.interfaces.dtos.inputs.TastingInputDTO;
import com.vinhonotas.degustacao.interfaces.dtos.outputs.TastingOutputDTO;
import com.vinhonotas.degustacao.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TastingController.class)
class TastingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TastingService tastingService;
    @MockBean
    private TastingConverter tastingConverter;

    private TastingEntity entity;
    private TastingInputDTO inputDTO;
    private TastingOutputDTO outputDTO;

    @BeforeEach
    void setUp() {
        entity = createTastingEntity();
        inputDTO = createTastingInputDTO();
        outputDTO = createTastingOutputDTO();
    }

    @Test
    @DisplayName("Deve criar uma degustação")
    void testCreateTasting() throws Exception {
        when(tastingService.create(inputDTO)).thenReturn(entity);
        when(tastingConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(post("/api/v1/tasting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar uma degustação")
    void testCreateTastingError() throws Exception {
        when(tastingService.create(inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_TASTING));

        mockMvc.perform(post("/api/v1/tasting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as degustações cadastradas")
    void testGetAllTasting() throws Exception {
        when(tastingService.getAll()).thenReturn(List.of(entity));
        when(tastingConverter.toOutputDTOList(List.of(entity))).thenReturn(List.of(outputDTO));

        mockMvc.perform(get("/api/v1/tasting")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(outputDTO))));
    }

    @Test
    @DisplayName("Deve lançar erro ao retornar uma lista com todas as degustações cadastradas")
    void testGetAllTastingError() throws Exception {
        when(tastingService.getAll()).thenThrow(new BadRequestException(MessagesConstants.TASTING_NOT_FOUND));

        mockMvc.perform(get("/api/v1/tasting")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma degustação cadastrada pelo id")
    void testGetTastingById() throws Exception {
        when(tastingService.getById(entity.getId())).thenReturn(entity);
        when(tastingConverter.toOutputDTO(entity)).thenReturn(outputDTO);

        mockMvc.perform(get("/api/v1/tasting/" + entity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar erro ao retornar uma degustação cadastrada pelo id")
    void testGetTastingByIdError() throws Exception {
        when(tastingService.getById(entity.getId())).thenThrow(new BadRequestException(MessagesConstants.TASTING_NOT_FOUND));

        mockMvc.perform(get("/api/v1/tasting/" + entity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar uma degustação cadastrada pelo id")
    void testUpdateTasting() throws Exception {
        when(tastingService.update(entity.getId(), inputDTO)).thenReturn(entity);
        when(tastingConverter.toOutputDTOUpdate(
                tastingService.update(entity.getId(), inputDTO),
                entity.getId(),
                tastingConverter.toOutputDTO(tastingService.update(entity.getId(), inputDTO))))
                .thenReturn(outputDTO);

        mockMvc.perform(put("/api/v1/tasting/" + entity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(outputDTO)));
    }

    @Test
    @DisplayName("Deve lançar erro ao atualizar uma degustação cadastrada pelo id")
    void testUpdateTastingError() throws Exception {
        when(tastingService.update(entity.getId(), inputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING_TASTING));

        mockMvc.perform(put("/api/v1/tasting/" + entity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar uma degustação cadastrada pelo id")
    void testDeleteTasting() throws Exception {
        mockMvc.perform(delete("/api/v1/tasting/" + entity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar erro ao deletar uma degustação cadastrada pelo id")
    void testDeleteTastingError() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_TASTING)).when(tastingService).delete(entity.getId());

        mockMvc.perform(delete("/api/v1/tasting/" + entity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private TastingOutputDTO createTastingOutputDTO() {
        return TastingOutputDTO.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(Mockito.mock(TastingCardEntity.class)))
                .build();
    }

    private TastingInputDTO createTastingInputDTO() {
        return TastingInputDTO.builder()
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(new TastingCardEntity()))
                .build();
    }

    private TastingEntity createTastingEntity() {
        return TastingEntity.builder()
                .id(UUID.fromString("f5e7e3e3-3e3e-4e3e-8e3e-3e3e3e3e3e3e"))
                .tastingData(LocalDate.now())
                .tastingType(EnumTastingType.COMPARATIVE)
                .tastingCards(Set.of(Mockito.mock(TastingCardEntity.class)))
                .build();
    }


}