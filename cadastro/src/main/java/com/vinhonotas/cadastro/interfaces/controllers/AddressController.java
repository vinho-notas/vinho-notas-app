package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.AddressConverter;
import com.vinhonotas.cadastro.application.services.AddressService;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.AddressOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressConverter addressConverter;

    @PostMapping
    public ResponseEntity<AddressOutputDTO> createAddress(@Valid @RequestBody AddressInputDTO addressInputDTO) {
        return ResponseEntity.ok(addressConverter.convertToOutputDTO(addressService.create(addressInputDTO)));
    }

    @GetMapping
    public ResponseEntity<List<AddressOutputDTO>> getAllAddress() {
        return ResponseEntity.ok(addressConverter.convertToOutputDTOList(addressService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressOutputDTO> getAddressById(@PathVariable("id") String id) {
        return ResponseEntity.ok(addressConverter.convertToOutputDTO(addressService.getById(UUID.fromString(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressOutputDTO> updateAddress(@PathVariable("id") String id, @Valid @RequestBody AddressInputDTO addressInputDTO) {
        return ResponseEntity.ok(addressConverter.convertToOutputDTOUpdate(addressService.update(UUID.fromString(id),
                addressInputDTO), UUID.fromString(id), addressConverter.convertToOutputDTO(addressService.update(UUID.fromString(id), addressInputDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") String id) {
        addressService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
