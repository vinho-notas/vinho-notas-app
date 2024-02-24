package com.vinhonotas.bff.client.cadastro;

import com.vinhonotas.bff.interfaces.dtos.outputs.AddressOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "address", url = "${cadastro-api.url}")
public interface AddressClient {

    @GetMapping("/address")
    List<AddressOutputDTO> getAllAddress();

    @GetMapping("/address/{id}")
    AddressOutputDTO getAddressById(@PathVariable("id") String id);

}
