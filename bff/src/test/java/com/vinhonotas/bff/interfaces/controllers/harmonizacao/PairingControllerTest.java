package com.vinhonotas.bff.interfaces.controllers.harmonizacao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhonotas.bff.application.services.harmonizacao.PairingService;
import com.vinhonotas.bff.interfaces.dtos.inputs.harmonizacao.PairingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.harmonizacao.PairingResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PairingControllerTest {

    private final String WINE_NAME = "Vinho Miolo Lote 43";

    private final String INFORMATION = "As características do vinho Vinho Miolo Lote 43 podem variar de acordo com a " +
            "safra e o processo de produção, mas geralmente são:" +
            "\n- Tipo de vinho: Tinto" +
            "\n- País de origem: Brasil" +
            "\n- Região: Vale dos Vinhedos, Rio Grande do Sul" +
            "\n- Uvas utilizadas: Cabernet Sauvignon e Merlot" +
            "\n- Teor alcoólico: Normalmente em torno de 13,5%" +
            "\n- Notas aromáticas: Frutas vermelhas, especiarias, notas de tabaco e baunilha" +
            "\n- Cor: Vermelho intenso" +
            "\n- Corpo: Encorpado" +
            "\n- Taninos: Macios e aveludados" +
            "\n- Acidez: Equilibrada";

    private final String PAIRING = "O Vinho Miolo Lote 43 harmoniza bem com:" +
            "\n- Carnes vermelhas grelhadas" +
            "\n- Massas com molhos vermelhos" +
            "\n- Queijos maduros" +
            "\n- Risotos de funghi" +
            "\n- Comida mexicana";

    private final String MENU_PAIRING = "Para harmonizar com o Vinho Miolo Lote 43, sugerimos o seguinte menu:" +
            "\n- Entrada: Carpaccio de carne com rúcula e lascas de queijo parmesão" +
            "\n- Prato Principal: Risoto de cogumelos selvagens" +
            "\n- Sobremesa: Tiramisù";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PairingService pairingService;

    private PairingInputDTO wine;

    @BeforeEach
    void setUp() {
        wine = new PairingInputDTO(WINE_NAME);
    }

    @Test
    @DisplayName("Deve retornar informações do vinho")
    void testGetWineInformation() throws Exception {
        PairingInputDTO inputDTO = new PairingInputDTO(WINE_NAME);
        PairingResponseDTO responseDTO = new PairingResponseDTO(INFORMATION);
        when(pairingService.getWineInformation(inputDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pairing/information")
                        .param("wine", objectMapper.writeValueAsString(inputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve retornar harmonizações do vinho")
    void testGetWinePairing() throws Exception {
        PairingInputDTO inputDTO = new PairingInputDTO(WINE_NAME);
        PairingResponseDTO responseDTO = new PairingResponseDTO(PAIRING);
        when(pairingService.getWinePairing(inputDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pairing/pairings")
                        .param("wine", objectMapper.writeValueAsString(inputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve retornar menu harmonizado com o vinho")
    void testGetMenuPairing() throws Exception {
        PairingInputDTO inputDTO = new PairingInputDTO(WINE_NAME);
        PairingResponseDTO responseDTO = new PairingResponseDTO(MENU_PAIRING);
        when(pairingService.getMenuPairing(inputDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pairing/menu")
                        .param("wine", objectMapper.writeValueAsString(inputDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
