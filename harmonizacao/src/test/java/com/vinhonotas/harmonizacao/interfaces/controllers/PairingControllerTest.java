package com.vinhonotas.harmonizacao.interfaces.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.harmonizacao.application.services.PairingService;
import com.vinhonotas.harmonizacao.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.harmonizacao.interfaces.dtos.outputs.PairingResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PairingControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private PairingService pairingService;

        private WineInputDTO wine;

        @BeforeEach
        void setUp() {
            wine = new WineInputDTO("On The Road Malbec From Patagônia 2020");
        }

        @Test
        void testGetWineInformation() throws Exception {
            PairingResponseDTO expectedResponse = new PairingResponseDTO("Wine information");
            when(pairingService.getWineInformation(wine)).thenReturn(expectedResponse);

            mockMvc.perform(get("/api/v1/pairing/information")
                            .param("wine", objectMapper.writeValueAsString(wine))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        void testGetWinePairing() throws Exception {
            PairingResponseDTO expectedResponse = new PairingResponseDTO("Wine pairing");
            when(pairingService.getWinePairing(wine)).thenReturn(expectedResponse);

            mockMvc.perform(get("/api/v1/pairing/pairings")
                            .param("wine", objectMapper.writeValueAsString(wine))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        void testGetMenuPairing() throws Exception {
            PairingResponseDTO expectedResponse = new PairingResponseDTO("Menu pairing");
            when(pairingService.getMenuPairing(wine)).thenReturn(expectedResponse);

            mockMvc.perform(get("/api/v1/pairing/menu")
                            .param("wine", objectMapper.writeValueAsString(wine))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

    }
