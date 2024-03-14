package com.vinhonotas.bff.client.avaliacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.avaliacao.PointScaleInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.avaliacao.PointScaleOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "review", url = "${avaliacao-api.url}", configuration = FeignConfig.class)
public interface PointScaleClient {

    @PostMapping("/review")
    PointScaleOutputDTO createPointScale(@Valid @RequestBody PointScaleInputDTO pointScaleInputDTO);

    @GetMapping("/review")
    List<PointScaleOutputDTO> getAllPointScale();

    @GetMapping("/review/{id}")
    PointScaleOutputDTO getPointScaleById(@PathVariable("id") String id);

    @PutMapping("/review/{id}")
    PointScaleOutputDTO updatePointScale(@PathVariable("id") String id, @Valid @RequestBody PointScaleInputDTO pointScaleInputDTO);

    @DeleteMapping("/review/{id}")
    void deletePointScale(@PathVariable("id") String id);

}
