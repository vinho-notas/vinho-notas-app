package com.vinhonotas.bff.client.vinho;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.vinho.WineOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "wine", url = "$(vinho-api.url)", configuration = FeignConfig.class)
public interface WineClient {

    @PostMapping("/wine")
    WineOutputDTO createWine(@Valid @RequestBody WineInputDTO wineInputDTO);

    @GetMapping("/wine")
    List<WineOutputDTO> getAllWines();

    @GetMapping("/wine/{id}")
    WineOutputDTO getWineById(@PathVariable("id") String id);

    @PutMapping("/wine/{id}")
    WineOutputDTO updateWine(@PathVariable("id") String id, @Valid @RequestBody WineInputDTO wineInputDTO);

    @DeleteMapping("/wine/{id}")
    void deleteWine(@PathVariable("id") String id);

}
