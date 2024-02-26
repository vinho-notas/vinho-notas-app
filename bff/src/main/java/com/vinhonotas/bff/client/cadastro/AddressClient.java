package com.vinhonotas.bff.client.cadastro;

import com.vinhonotas.bff.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.AddressOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "address", url = "${cadastro-api.url}")
public interface AddressClient {

    @PostMapping("/address")
    AddressOutputDTO createAddress(@Valid @RequestBody AddressInputDTO addressInputDTO);
    @GetMapping("/address")
    List<AddressOutputDTO> getAllAddress();

    @GetMapping("/address/{id}")
    AddressOutputDTO getAddressById(@PathVariable("id") String id);

    @PutMapping("/{id}")
    AddressOutputDTO updateAddress(@PathVariable("id") String id, @Valid @RequestBody AddressInputDTO addressInputDTO);

    @DeleteMapping("/{id}")
    void deleteAddress(@PathVariable("id") String id);

}
