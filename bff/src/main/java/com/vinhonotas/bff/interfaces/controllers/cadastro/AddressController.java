package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.vinhonotas.bff.application.services.cadastro.AddressService;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.EditAddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RateLimiter(name = "rateLimiter")
@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Tag(name = "Endereços", description = "Operações relacionadas a endereços")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "Cria um endereço")
    @PostMapping
    public ResponseEntity<AddressOutputDTO> createAddress(@Valid @RequestBody AddressInputDTO addressInputDTO) {
        return ResponseEntity.ok(addressService.createAddress(addressInputDTO));
    }

    @Operation(summary = "Retorna todos os endereços")
    @GetMapping
    public ResponseEntity<List<AddressOutputDTO>> getAllAddress() {
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @Operation(summary = "Retorna um endereço pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<AddressOutputDTO> getAddressById(@PathVariable("id") String id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @Operation(summary = "Atualiza um endereço")
    @PutMapping("/{id}")
    public ResponseEntity<AddressOutputDTO> updateAddress(@PathVariable("id") String id, @Valid @RequestBody EditAddressInputDTO editAddressInputDTO) {
        return ResponseEntity.ok(addressService.updateAddress(id, editAddressInputDTO));
    }

    @Operation(summary = "Deleta um endereço")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") String id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta uma lista de endereços")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllAddress(@RequestBody List<String> ids) {
        ids.forEach(addressService::deleteAddress);
        return ResponseEntity.noContent().build();
    }

}
