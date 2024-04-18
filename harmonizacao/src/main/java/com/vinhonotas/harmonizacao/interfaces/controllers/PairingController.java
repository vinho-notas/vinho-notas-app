package com.vinhonotas.harmonizacao.interfaces.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pairing")
@RequiredArgsConstructor
@Slf4j
public class PairingController {

    private final OpenAiChatClient openAiChatClient;

    @GetMapping("/information")
    public String getWineInformation(@RequestParam(value = "wine") String wine) {
        PromptTemplate promptTemplate = new PromptTemplate("Quais as características do vinho {wine}?");
        log.info("getWineInformation :: Buscando informações sobre o vinho: {}", wine);
        promptTemplate.add("wine", wine);
        String result = openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
        log.info("getWineInformation :: Informações encontradas: {}", result);
        return result;
    }
}
