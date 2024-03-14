package com.vinhonotas.bff.client.degustacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.degustacao.VisualInspectionInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.degustacao.VisualInspectionOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "visual-inspection", url = "${degustacao-api.url}", configuration = FeignConfig.class)
public interface VisualInspectionClient {

    @PostMapping("/visual-inspection")
    VisualInspectionOutputDTO createVisualInspection(@Valid @RequestBody VisualInspectionInputDTO visualInspectionInputDTO);

    @GetMapping("/visual-inspection")
    List<VisualInspectionOutputDTO> getAllVisualInspections();

    @GetMapping("/visual-inspection/{id}")
    VisualInspectionOutputDTO getVisualInspectionById(@PathVariable("id") String id);

    @PutMapping("/visual-inspection/{id}")
    VisualInspectionOutputDTO updateVisualInspection(@PathVariable ("id") String id, @Valid @RequestBody VisualInspectionInputDTO visualInspectionInputDTO);

    @DeleteMapping("/visual-inspection/{id}")
    void deleteVisualInspection(@PathVariable ("id") String id);

}
