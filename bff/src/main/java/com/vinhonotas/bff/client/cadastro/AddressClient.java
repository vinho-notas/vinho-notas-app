package com.vinhonotas.bff.client.cadastro;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "address", url = "${cadastro-api.url}", configuration = FeignConfig.class)
public interface AddressClient {

    @PostMapping("/address")
    AddressOutputDTO createAddress(@Valid @RequestBody AddressInputDTO addressInputDTO);

    @GetMapping("/address")
    List<AddressOutputDTO> getAllAddress();

    @GetMapping("/address/{id}")
    AddressOutputDTO getAddressById(@PathVariable("id") String id);

    @PutMapping("/address/{id}")
    AddressOutputDTO updateAddress(@PathVariable("id") String id, @Valid @RequestBody AddressInputDTO addressInputDTO);

    @DeleteMapping("/address/{id}")
    void deleteAddress(@PathVariable("id") String id);

}
