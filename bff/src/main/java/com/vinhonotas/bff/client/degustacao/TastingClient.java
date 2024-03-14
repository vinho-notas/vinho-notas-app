package com.vinhonotas.bff.client.degustacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.TastingInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.TastingOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "tasting", url = "${degustacao-api.url}", configuration = FeignConfig.class)
public interface TastingClient {

    @PostMapping("/tasting")
    TastingOutputDTO createTasting(@Valid @RequestBody TastingInputDTO tastingInputDTO);

    @GetMapping("/tasting")
    List<TastingOutputDTO> getAllTastings();

    @GetMapping("/tasting/{id}")
    TastingOutputDTO getTastingById(@PathVariable ("id") String id);

    @PutMapping("/tasting/{id}")
    TastingOutputDTO updateTasting(@PathVariable ("id") String id, @Valid @RequestBody TastingInputDTO tastingInputDTO);

    @DeleteMapping("/tasting/{id}")
    void deleteTasting(@PathVariable ("id") String id);

}
