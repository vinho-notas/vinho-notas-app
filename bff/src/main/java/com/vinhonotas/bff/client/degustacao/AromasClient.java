package com.vinhonotas.bff.client.degustacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.AromasInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.AromasOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "aromas", url = "$(degustacao-api.url)", configuration = FeignConfig.class)
public interface AromasClient {

    @PostMapping("/aromas")
    public ResponseEntity<AromasOutputDTO> createAromas(@Valid @RequestBody AromasInputDTO aromasInputDTO);

    @GetMapping("/aromas")
    List<AromasOutputDTO> getAllAromas();

    @GetMapping("/aromas/{id}")
    AromasOutputDTO getAromasById(@PathVariable("id") String id);

    @PutMapping("/aromas/{id}")
    AromasOutputDTO updateAromas(@PathVariable ("id") String id, @Valid @RequestBody AromasInputDTO aromasInputDTO);

    @DeleteMapping("/aromas/{id}")
    void deleteAromas(@PathVariable ("id") String id);

}
