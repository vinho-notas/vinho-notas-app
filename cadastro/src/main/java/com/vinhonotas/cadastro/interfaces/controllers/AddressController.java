package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.AddressConverter;
import com.vinhonotas.cadastro.application.services.AddressService;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AddressInputDTO;
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
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
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
    public ResponseEntity<AddressOutputDTO> updateAddress(@PathVariable("id") String id, @Valid @RequestBody AddressInputDTO addressInputDTO) {
        return ResponseEntity.ok(addressConverter.convertToOutputDTOUpdate(addressService.update(UUID.fromString(id),
                addressInputDTO), UUID.fromString(id), addressConverter.convertToOutputDTO(addressService.update(UUID.fromString(id), addressInputDTO))));
    }

    @Operation(summary = "Deleta um endereço")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") String id) {
        addressService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
