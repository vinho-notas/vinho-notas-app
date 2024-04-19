package com.vinhonotas.harmonizacao.application.services.impl;

import com.vinhonotas.harmonizacao.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.harmonizacao.interfaces.dtos.outputs.PairingResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PairingServiceImplTest {

    @InjectMocks
    private PairingServiceImpl pairingService;

    @Mock
    private OpenAiChatClient openAiChatClient;

    private WineInputDTO wine;
    private PromptTemplate promptTemplate;
    private PairingResponseDTO responseDTO;
    private ChatResponse chatResponse;

    @BeforeEach
    public void setUp() {
        wine = new WineInputDTO("On The Road Malbec From Patagônia 2020");
    }

    @Test
    @DisplayName("Deve retornar informações do vinho")
    public void testGetWineInformation() {
        promptTemplate = new PromptTemplate("Quais as características do vinho {wine}?");
        promptTemplate.add("wine", wine);
        responseDTO = new PairingResponseDTO("Informações sobre o vinho");
        Generation generation = new Generation(responseDTO.response());
        chatResponse = new ChatResponse(List.of(generation));

        when(openAiChatClient.call(promptTemplate.create())).thenReturn(chatResponse);
        PairingResponseDTO response = assertDoesNotThrow(() -> pairingService.getWineInformation(wine));

        assertNotNull(response);
        assertEquals(responseDTO.response(), response.response());
        verify(openAiChatClient).call(promptTemplate.create());
    }

    @Test
    @DisplayName("Deve retornar harmonizações do vinho")
    public void testGetWinePairing() {
        promptTemplate = new PromptTemplate("Quais os pratos que combinam com o vinho {wine}?");
        promptTemplate.add("wine", wine);
        responseDTO = new PairingResponseDTO("Pratos que combinam com o vinho: combinação 1, combinação 2, combinação 3");
        Generation generation = new Generation(responseDTO.response());
        chatResponse = new ChatResponse(List.of(generation));

        when(openAiChatClient.call(promptTemplate.create())).thenReturn(chatResponse);
        PairingResponseDTO response = assertDoesNotThrow(() -> pairingService.getWinePairing(wine));

        assertNotNull(response);
        assertEquals(responseDTO.response(), response.response());
        verify(openAiChatClient).call(promptTemplate.create());
    }

    @Test
    @DisplayName("Deve retornar menu harmonizado com o vinho")
    public void testGetMenuPairing() {
        promptTemplate = new PromptTemplate("Crie um menu com entrada, prato principal e sobremesa que harmonize com o vinho {wine}?");
        promptTemplate.add("wine", wine);
        responseDTO = new PairingResponseDTO("Menu harmonizado: entrada, prato principal, sobremesa");
        Generation generation = new Generation(responseDTO.response());
        chatResponse = new ChatResponse(List.of(generation));

        when(openAiChatClient.call(promptTemplate.create())).thenReturn(chatResponse);
        PairingResponseDTO response = assertDoesNotThrow(() -> pairingService.getMenuPairing(wine));

        assertNotNull(response);
        assertEquals(responseDTO.response(), response.response());
        verify(openAiChatClient).call(promptTemplate.create());
    }

}
