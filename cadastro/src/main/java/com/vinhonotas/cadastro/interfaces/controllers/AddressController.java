package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.AddressConverter;
import com.vinhonotas.cadastro.application.services.AddressService;
import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.EditAddressInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.AddressOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/address")
@Tag(name = "Endereços", description = "Operações relacionadas a endereços")
public class AddressController {

    private final AddressService addressService;
    private final AddressConverter addressConverter;

    @Operation(summary = "Cria um endereço")
    @PostMapping
    public ResponseEntity<AddressOutputDTO> createAddress(@Valid @RequestBody AddressInputDTO addressInputDTO) {
        return ResponseEntity.ok(addressConverter.convertToOutputDTO(addressService.create(addressInputDTO)));
    }

    @Operation(summary = "Retorna todos os endereços")
    @GetMapping
    public ResponseEntity<List<AddressOutputDTO>> getAllAddress() {
        return ResponseEntity.ok(addressConverter.convertToOutputDTOList(addressService.getAll()));
    }

    @Operation(summary = "Retorna um endereço pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<AddressOutputDTO> getAddressById(@PathVariable("id") String id) {
        return ResponseEntity.ok(addressConverter.convertToOutputDTO(addressService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Atualiza um endereço")
    @PutMapping("/{id}")
    public ResponseEntity<AddressOutputDTO> updateAddress(@PathVariable("id") String id, @Valid @RequestBody EditAddressInputDTO editAddressInputDTO) {
        AddressEntity addressUpdated = addressService.update(UUID.fromString(id), editAddressInputDTO);
        AddressOutputDTO addressOutputDTO = addressConverter.convertToOutputDTO(addressUpdated);

        return ResponseEntity.ok(addressOutputDTO);
    }

    @Operation(summary = "Deleta um endereço")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") String id) {
        addressService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
