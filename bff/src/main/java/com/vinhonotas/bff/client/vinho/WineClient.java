package com.vinhonotas.bff.client.vinho;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.vinho.WineInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.vinho.WineOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "wine", url = "${vinho-api.url}", configuration = FeignConfig.class)
public interface WineClient {

    @PostMapping("/wines")
    WineOutputDTO createWine(@Valid @RequestBody WineInputDTO wineInputDTO);

    @GetMapping("/wines")
    List<WineOutputDTO> getAllWines();

    @GetMapping("/wines/{id}")
    WineOutputDTO getWineById(@PathVariable("id") String id);

    @PutMapping("/wines/{id}")
    WineOutputDTO updateWine(@PathVariable("id") String id, @Valid @RequestBody WineInputDTO wineInputDTO);

    @DeleteMapping("/wines/{id}")
    void deleteWine(@PathVariable("id") String id);

}
