package com.vinhonotas.bff.client.degustacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingCardInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingCardOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "tasting-card", url = "${degustacao-api.url}", configuration = FeignConfig.class)
public interface TastingCardClient {

    @PostMapping("/tasting-card")
    TastingCardOutputDTO createTastingCard(@Valid @RequestBody TastingCardInputDTO tastingCardInputDTO);

    @GetMapping("/tasting-card")
    List<TastingCardOutputDTO> getAllTastingCards();

    @GetMapping("/tasting-card/{id}")
    TastingCardOutputDTO getTastingCardById(@PathVariable("id") String id);

    @PutMapping("/tasting-card/{id}")
    TastingCardOutputDTO updateTastingCard(@PathVariable ("id") String id, @Valid @RequestBody TastingCardInputDTO tastingCardInputDTO);

    @DeleteMapping("/tasting-card/{id}")
    void deleteTastingCard(@PathVariable ("id") String id);
    
}
