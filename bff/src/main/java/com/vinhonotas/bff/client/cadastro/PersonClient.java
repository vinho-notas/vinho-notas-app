package com.vinhonotas.bff.client.cadastro;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.PersonOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "person", url = "${cadastro-api.url}", configuration = FeignConfig.class)
public interface PersonClient {

    @PostMapping("/persons")
    PersonOutputDTO createPerson(@Valid @RequestBody PersonInputDTO personInputDTO);

    @GetMapping("/persons")
    List<PersonOutputDTO> getAllPerson();

    @GetMapping("/persons/{id}")
    PersonOutputDTO getPersonById(@PathVariable("id") String id);

    @GetMapping("/persons/name/{name}")
    PersonOutputDTO getPersonByName(@PathVariable("name") String name);

    @PutMapping("/persons/{id}")
    PersonOutputDTO updatePerson(@PathVariable("id") String id, @Valid @RequestBody PersonInputDTO personInputDTO);

    @DeleteMapping("/persons/{id}")
    Void deletePerson(@PathVariable("id") String id);

}
