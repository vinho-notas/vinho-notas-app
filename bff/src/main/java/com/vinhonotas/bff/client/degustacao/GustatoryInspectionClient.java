package com.vinhonotas.bff.client.degustacao;

import com.vinhonotas.bff.configuration.FeignConfig;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "gustatory-inspection", url = "${degustacao-api.url}", configuration = FeignConfig.class)
public interface GustatoryInspectionClient {

    @PostMapping("/gustatory-inspection")
    GustatoryInspectionOutputDTO createGustatoryInspection(@Valid @RequestBody GustatoryInspectionInputDTO gustatoryInspectionInputDTO);

    @GetMapping("/gustatory-inspection")
    List<GustatoryInspectionOutputDTO> getAllGustatoryInspections();

    @GetMapping("/gustatory-inspection/{id}")
    GustatoryInspectionOutputDTO getGustatoryInspectionById(@PathVariable("id") String id);

    @PutMapping("/gustatory-inspection/{id}")
    GustatoryInspectionOutputDTO updateGustatoryInspection(@PathVariable ("id") String id,
                                                                                  @Valid @RequestBody GustatoryInspectionInputDTO gustatoryInspectionInputDTO);

    @DeleteMapping("/gustatory-inspection/{id}")
    void deleteGustatoryInspection(@PathVariable ("id") String id);

}
