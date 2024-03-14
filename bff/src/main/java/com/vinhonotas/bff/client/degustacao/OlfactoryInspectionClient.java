package com.vinhonotas.bff.client.degustacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.OlfactoryInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.OlfactoryInspectionOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "olfactory-inspection", url = "${degustacao-api.url}", configuration = FeignConfig.class)
public interface OlfactoryInspectionClient {

    @PostMapping("/olfactory-inspection")
    OlfactoryInspectionOutputDTO createOlafactoryInspection(@Valid @RequestBody OlfactoryInspectionInputDTO olfactoryInspectionInputDTO);

    @GetMapping("/olfactory-inspection")
    List<OlfactoryInspectionOutputDTO> getAllOlfactoryInspections();

    @GetMapping("/olfactory-inspection/{id}")
    OlfactoryInspectionOutputDTO getOlfactoryInspectionById(@PathVariable("id") String id);

    @PutMapping("/olfactory-inspection/{id}")
    OlfactoryInspectionOutputDTO updateOlfactoryInspection(@PathVariable ("id") String id, @Valid @RequestBody OlfactoryInspectionInputDTO olfactoryInspectionInputDTO);

    @DeleteMapping("/olfactory-inspection/{id}")
    void deleteOlfactoryInspection(@PathVariable ("id") String id);

}
