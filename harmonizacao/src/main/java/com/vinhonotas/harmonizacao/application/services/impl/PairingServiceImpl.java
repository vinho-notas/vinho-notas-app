package com.vinhonotas.harmonizacao.application.services.impl;

import com.vinhonotas.harmonizacao.application.services.PairingService;
import com.vinhonotas.harmonizacao.interfaces.dtos.inputs.WineInputDTO;
import com.vinhonotas.harmonizacao.interfaces.dtos.outputs.PairingResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PairingServiceImpl implements PairingService {

    private final OpenAiChatClient openAiChatClient;

    @Override
    public PairingResponseDTO getWineInformation(WineInputDTO wine) {
        PromptTemplate promptTemplate = new PromptTemplate("Quais as características do vinho {wine}?");
        log.info("getWineInformation :: Buscando informações sobre o vinho: {}", wine);
        promptTemplate.add("wine", wine);
        String result = openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
        log.info("getWineInformation :: Informações encontradas: {}", result);
        return new PairingResponseDTO(result);
    }

    @Override
    public PairingResponseDTO getWinePairing(WineInputDTO wine) {
        PromptTemplate promptTemplate = new PromptTemplate("Quais os pratos que combinam com o vinho {wine}?");
        log.info("getWinePairing :: Buscando harmonizações para o vinho: {}", wine);
        promptTemplate.add("wine", wine);

        String result = openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
        log.info("getWinePairing :: Harmonizações encontradas: {}", result);
        return new PairingResponseDTO(result);
    }

    @Override
    public PairingResponseDTO getMenuPairing(WineInputDTO wine) {
        PromptTemplate promptTemplate = new PromptTemplate("Crie um menu com entrada, prato principal e sobremesa que harmonize com o vinho {wine}?");
        log.info("getMenuPairing :: Buscando menu harmonizado para o vinho: {}", wine);
        promptTemplate.add("wine", wine);
        String result = openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
        log.info("getMenuPairing :: Menu harmonizado: {}", result);
        return new PairingResponseDTO(result);
    }
    
}
