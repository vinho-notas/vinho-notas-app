package com.vinhonotas.bff.client.cadastro;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.outputs.StateOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "state", url = "${cadastro-api.url}", configuration = FeignConfig.class)
public interface StateClient {

    @GetMapping("/states")
    List<StateOutputDTO> getAllStates();

    @GetMapping("/states/{id}")
    StateOutputDTO getStateById(@PathVariable("id") String id);


    @GetMapping("/states/name/{name}")
    StateOutputDTO getStateByName(@PathVariable("name") String name);

    @GetMapping("/states/uf/{uf}")
    StateOutputDTO getStateByUf(@PathVariable("uf") String uf);

}
