package com.vinhonotas.bff.client.degustacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "aromas", url = "${degustacao-api.url}", configuration = FeignConfig.class)
public interface AromasClient {

    @PostMapping("/aromas")
    AromasOutputDTO createAromas(@Valid @RequestBody AromasInputDTO aromasInputDTO);

    @GetMapping("/aromas")
    List<AromasOutputDTO> getAllAromas();

    @GetMapping("/aromas/{id}")
    AromasOutputDTO getAromasById(@PathVariable("id") String id);

    @PutMapping("/aromas/{id}")
    AromasOutputDTO updateAromas(@PathVariable ("id") String id, @Valid @RequestBody AromasInputDTO aromasInputDTO);

    @DeleteMapping("/aromas/{id}")
    void deleteAromas(@PathVariable ("id") String id);

}
