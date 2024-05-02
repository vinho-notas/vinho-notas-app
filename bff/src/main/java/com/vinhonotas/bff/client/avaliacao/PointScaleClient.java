package com.vinhonotas.bff.client.avaliacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao.PointScaleInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao.PointScaleOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "point-scales", url = "${avaliacao-api.url}", configuration = FeignConfig.class)
public interface PointScaleClient {

    @PostMapping("/point-scales")
    PointScaleOutputDTO createPointScale(@Valid @RequestBody PointScaleInputDTO pointScaleInputDTO);

    @GetMapping("/point-scales")
    List<PointScaleOutputDTO> getAllPointScale();

    @GetMapping("/point-scales/{id}")
    PointScaleOutputDTO getPointScaleById(@PathVariable("id") String id);

    @PutMapping("/point-scales/{id}")
    PointScaleOutputDTO updatePointScale(@PathVariable("id") String id, @Valid @RequestBody PointScaleInputDTO pointScaleInputDTO);

    @DeleteMapping("/point-scales/{id}")
    void deletePointScale(@PathVariable("id") String id);

}
