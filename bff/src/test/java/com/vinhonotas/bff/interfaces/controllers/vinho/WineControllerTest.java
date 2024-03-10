package com.vinhonotas.bff.interfaces.controllers.vinho;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.vinho.WineService;
import com.vinhonotas.bff.domain.enums.EnumWineClassification;
import com.vinhonotas.bff.domain.enums.EnumWineType;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.vinho.WineOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
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

    private static final String URL = "/api/v1/wines";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WineService wineService;

    private WineInputDTO wineInputDTO;
    private WineOutputDTO wineOutputDTO;

    @BeforeEach
    void setUp() {
        wineInputDTO = createWineInputDTO();
        wineOutputDTO = createWineOutputDTO();
    }

    @Test
    @DisplayName("Deve criar um vinho")
    void testCreateWine() throws Exception {
        when(wineService.createWine(wineInputDTO)).thenReturn(wineOutputDTO);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(wineOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(wineOutputDTO.getName()))
                .andExpect(jsonPath("$.price").value(wineOutputDTO.getPrice()))
                .andExpect(jsonPath("$.purchaseLocation").value(wineOutputDTO.getPurchaseLocation()))
                .andExpect(jsonPath("$.purchaseDate").value(wineOutputDTO.getPurchaseDate().toString()))
                .andExpect(jsonPath("$.wineType").value(wineOutputDTO.getWineType().toString()))
                .andExpect(jsonPath("$.wineClassification").value(wineOutputDTO.getWineClassification().toString()))
                .andExpect(jsonPath("$.alcoholContent").value(wineOutputDTO.getAlcoholContent()))
                .andExpect(jsonPath("$.volumeMl").value(wineOutputDTO.getVolumeMl()))
                .andExpect(jsonPath("$.grape").value(wineOutputDTO.getGrape()))
                .andExpect(jsonPath("$.winery").value(wineOutputDTO.getWinery()))
                .andExpect(jsonPath("$.serviceTemperature").value(wineOutputDTO.getServiceTemperature()))
                .andExpect(jsonPath("$.harvest").value(wineOutputDTO.getHarvest()))
                .andExpect(jsonPath("$.country").value(wineOutputDTO.getCountry()))
                .andExpect(jsonPath("$.guardTime").value(wineOutputDTO.getGuardTime()))
                .andExpect(jsonPath("$.region").value(wineOutputDTO.getRegion()))
                .andExpect(jsonPath("$.maturation").value(wineOutputDTO.getMaturation()))
                .andExpect(jsonPath("$.harmonization").value(wineOutputDTO.getHarmonization()));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar um vinho")
    void testCreateWineError() throws Exception {
        when(wineService.createWine(wineInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar uma lista de vinhos")
    void testGetAllWines() throws Exception {
        when(wineService.getAllWines()).thenReturn(List.of(wineOutputDTO));

        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(wineOutputDTO.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(wineOutputDTO.getName()))
                .andExpect(jsonPath("$[0].price").value(wineOutputDTO.getPrice()))
                .andExpect(jsonPath("$[0].purchaseLocation").value(wineOutputDTO.getPurchaseLocation()))
                .andExpect(jsonPath("$[0].purchaseDate").value(wineOutputDTO.getPurchaseDate().toString()))
                .andExpect(jsonPath("$[0].wineType").value(wineOutputDTO.getWineType().toString()))
                .andExpect(jsonPath("$[0].wineClassification").value(wineOutputDTO.getWineClassification().toString()))
                .andExpect(jsonPath("$[0].alcoholContent").value(wineOutputDTO.getAlcoholContent()))
                .andExpect(jsonPath("$[0].volumeMl").value(wineOutputDTO.getVolumeMl()))
                .andExpect(jsonPath("$[0].grape").value(wineOutputDTO.getGrape()))
                .andExpect(jsonPath("$[0].winery").value(wineOutputDTO.getWinery()))
                .andExpect(jsonPath("$[0].serviceTemperature").value(wineOutputDTO.getServiceTemperature()))
                .andExpect(jsonPath("$[0].harvest").value(wineOutputDTO.getHarvest()))
                .andExpect(jsonPath("$[0].country").value(wineOutputDTO.getCountry()))
                .andExpect(jsonPath("$[0].guardTime").value(wineOutputDTO.getGuardTime()))
                .andExpect(jsonPath("$[0].region").value(wineOutputDTO.getRegion()))
                .andExpect(jsonPath("$[0].maturation").value(wineOutputDTO.getMaturation()))
                .andExpect(jsonPath("$[0].harmonization").value(wineOutputDTO.getHarmonization()));
    }

    @Test
    @DisplayName("Deve retornar um erro ao buscar uma lista de vinhos")
    void testGetAllWinesError() throws Exception {
        when(wineService.getAllWines()).thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar um vinho pelo id")
    void testGetWineById() throws Exception {
        when(wineService.getWineById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(wineOutputDTO);

        mockMvc.perform(get(URL + "/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(wineOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(wineOutputDTO.getName()))
                .andExpect(jsonPath("$.price").value(wineOutputDTO.getPrice()))
                .andExpect(jsonPath("$.purchaseLocation").value(wineOutputDTO.getPurchaseLocation()))
                .andExpect(jsonPath("$.purchaseDate").value(wineOutputDTO.getPurchaseDate().toString()))
                .andExpect(jsonPath("$.wineType").value(wineOutputDTO.getWineType().toString()))
                .andExpect(jsonPath("$.wineClassification").value(wineOutputDTO.getWineClassification().toString()))
                .andExpect(jsonPath("$.alcoholContent").value(wineOutputDTO.getAlcoholContent()))
                .andExpect(jsonPath("$.volumeMl").value(wineOutputDTO.getVolumeMl()))
                .andExpect(jsonPath("$.grape").value(wineOutputDTO.getGrape()))
                .andExpect(jsonPath("$.winery").value(wineOutputDTO.getWinery()))
                .andExpect(jsonPath("$.serviceTemperature").value(wineOutputDTO.getServiceTemperature()))
                .andExpect(jsonPath("$.harvest").value(wineOutputDTO.getHarvest()))
                .andExpect(jsonPath("$.country").value(wineOutputDTO.getCountry()))
                .andExpect(jsonPath("$.guardTime").value(wineOutputDTO.getGuardTime()))
                .andExpect(jsonPath("$.region").value(wineOutputDTO.getRegion()))
                .andExpect(jsonPath("$.maturation").value(wineOutputDTO.getMaturation()))
                .andExpect(jsonPath("$.harmonization").value(wineOutputDTO.getHarmonization()));
    }

    @Test
    @DisplayName("Deve retornar um erro ao buscar um vinho pelo id")
    void testGetWineByIdError() throws Exception {
        when(wineService.getWineById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .thenThrow(new BadRequestException(MessagesConstants.NOT_FOUND));

        mockMvc.perform(get(URL + "/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar um vinho")
    void testUpdateWine() throws Exception {
        when(wineService.updateWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", wineInputDTO)).thenReturn(wineOutputDTO);

        mockMvc.perform(put(URL + "/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(wineOutputDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(wineOutputDTO.getName()))
                .andExpect(jsonPath("$.price").value(wineOutputDTO.getPrice()))
                .andExpect(jsonPath("$.purchaseLocation").value(wineOutputDTO.getPurchaseLocation()))
                .andExpect(jsonPath("$.purchaseDate").value(wineOutputDTO.getPurchaseDate().toString()))
                .andExpect(jsonPath("$.wineType").value(wineOutputDTO.getWineType().toString()))
                .andExpect(jsonPath("$.wineClassification").value(wineOutputDTO.getWineClassification().toString()))
                .andExpect(jsonPath("$.alcoholContent").value(wineOutputDTO.getAlcoholContent()))
                .andExpect(jsonPath("$.volumeMl").value(wineOutputDTO.getVolumeMl()))
                .andExpect(jsonPath("$.grape").value(wineOutputDTO.getGrape()))
                .andExpect(jsonPath("$.winery").value(wineOutputDTO.getWinery()))
                .andExpect(jsonPath("$.serviceTemperature").value(wineOutputDTO.getServiceTemperature()))
                .andExpect(jsonPath("$.harvest").value(wineOutputDTO.getHarvest()))
                .andExpect(jsonPath("$.country").value(wineOutputDTO.getCountry()))
                .andExpect(jsonPath("$.guardTime").value(wineOutputDTO.getGuardTime()))
                .andExpect(jsonPath("$.region").value(wineOutputDTO.getRegion()))
                .andExpect(jsonPath("$.maturation").value(wineOutputDTO.getMaturation()))
                .andExpect(jsonPath("$.harmonization").value(wineOutputDTO.getHarmonization()));
    }

    @Test
    @DisplayName("Deve retornar um erro ao atualizar um vinho")
    void testUpdateWineError() throws Exception {
        when(wineService.updateWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", wineInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        mockMvc.perform(put(URL + "/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wineInputDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve deletar um vinho")
    void testDeleteWine() throws Exception {
        mockMvc.perform(delete(URL + "/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar um erro ao deletar um vinho")
    void testDeleteWineError() throws Exception {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(wineService).deleteWine("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");

        mockMvc.perform(delete(URL + "/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .andDo(print())
                .andExpect(status().isBadRequest());
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