package com.vinhonotas.vinho.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.vinho.application.converters.WineConverter;
import com.vinhonotas.vinho.application.services.WineService;
import com.vinhonotas.vinho.application.services.exceptions.BadRequestException;
import com.vinhonotas.vinho.domain.entities.WineEntity;
import com.vinhonotas.vinho.domain.enums.EnumWineClassification;
import com.vinhonotas.vinho.domain.enums.EnumWineType;
import com.vinhonotas.vinho.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.vinho.interfaces.dtos.outputs.WineOutputDTO;
import com.vinhonotas.vinho.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WineController.class)
class WineControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WineService wineService;
    @MockBean
    private WineConverter wineConverter;

    private WineInputDTO redWineInputDTO;
    private WineOutputDTO redWineOutputDTO;
    private WineEntity redWineEntity;

    @BeforeEach
    void setUp() {
        redWineInputDTO = createRedWineInputDTO();
        redWineEntity = createRedWineEntity();
        redWineOutputDTO = createRedWineOutputDTO();
    }

    @Test
    @DisplayName("Deve criar um vinho")
    void testCreate() throws Exception {
        when(wineService.create(redWineInputDTO)).thenReturn(redWineEntity);
        when(wineConverter.toOutputDTO(redWineEntity)).thenReturn(redWineOutputDTO);

        mockMvc.perform(post("/api/v1/wines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(redWineInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar criar um vinho")
    void testCreateWithException() throws Exception {
        when(wineService.create(redWineInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_CREATE_WINE));

        mockMvc.perform(post("/api/v1/wines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(redWineInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista com todos os vinhos cadastrados")
    void testGetAll() throws Exception {
        when(wineService.getAll()).thenReturn(List.of(redWineEntity, redWineEntity));
        when(wineConverter.toOutputDTOList(List.of(redWineEntity, redWineEntity))).thenReturn(List.of(redWineOutputDTO, redWineOutputDTO));

        mockMvc.perform(get("/api/v1/wines")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar uma lista de vinhos")
    void testGetAllWithException() throws Exception {
        when(wineService.getAll()).thenThrow(new BadRequestException(MessagesConstants.ERROR_WINE_NOT_FOUND));

        mockMvc.perform(get("/api/v1/wines")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um vinho pelo id")
    void testGetById() throws Exception {
        when(wineService.getById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))).thenReturn(redWineEntity);
        when(wineConverter.toOutputDTO(redWineEntity)).thenReturn(redWineOutputDTO);

        mockMvc.perform(get("/api/v1/wines/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar retornar um vinho pelo id")
    void testGetByIdWithException() throws Exception {
        when(wineService.getById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WINE_NOT_FOUND));

        mockMvc.perform(get("/api/v1/wines/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um vinho pelo id")
    void testUpdate() throws Exception {
        redWineOutputDTO.setPrice(BigDecimal.valueOf(800.00));

        when(wineService.update(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), redWineInputDTO)).thenReturn(redWineEntity);
        when(wineConverter.toOutputDTO(redWineEntity)).thenReturn(redWineOutputDTO);
        when(wineConverter.toOutputDTOUpdate(redWineEntity, UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"),
                redWineOutputDTO)).thenReturn(redWineOutputDTO);

        mockMvc.perform(put("/api/v1/wines/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(redWineInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andExpect(jsonPath("$.price").value(800.00));
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar atualizar um vinho pelo id")
    void testUpdateWithException() throws Exception {
        when(wineService.update(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), redWineInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_UPDATE_WINE_DATA));

        mockMvc.perform(put("/api/v1/wines/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(redWineInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar um vinho pelo id")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/wines/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar deletar um vinho pelo id")
    void testDeleteWithException() throws Exception {
        doThrow(BadRequestException.class).when(wineService).delete(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));

        mockMvc.perform(delete("/api/v1/wines/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private WineOutputDTO createRedWineOutputDTO() {
        return WineOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE.getCode())
                .wineClassification(EnumWineClassification.DRYWINE.getCode())
                .alcoholContent("12.5")
                .volumeMl("750")
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

    private WineEntity createRedWineEntity() {
        return WineEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE)
                .wineClassification(EnumWineClassification.DRYWINE)
                .alcoholContent("12.5")
                .volumeMl(750)
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

    private WineInputDTO createRedWineInputDTO() {
        return WineInputDTO.builder()
                .name("Portada Winemaker's Selection 2020")
                .price(BigDecimal.valueOf(70.00))
                .purchaseLocation("www.evino.com.br")
                .purchaseDate(LocalDate.now())
                .wineType(EnumWineType.REDWINE.getCode())
                .wineClassification(EnumWineClassification.DRYWINE.getCode())
                .alcoholContent("12.5")
                .volumeMl("750")
                .grape("Uvas variadas")
                .winery("DFJ Vinhos")
                .serviceTemperature("17.0")
                .harvest("2020")
                .country("Portugal")
                .guardTime("2023")
                .region("Lisboa")
                .maturation("1 mês em garrafa")
                .harmonization("Carnes vermelhas, Queijos, Pato assado, polenta com ragus de sabor intenso, excelente com carnes de caça, queijos de massa dura, com longa maturação.")
                .build();
    }

}