package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.vinhonotas.bff.application.services.cadastro.AddressService;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressOutputDTO> createAddress(@Valid @RequestBody AddressInputDTO addressInputDTO) {
        return ResponseEntity.ok(addressService.createAddress(addressInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<AddressOutputDTO>> getAllAddress() {
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressOutputDTO> getAddressById(@PathVariable("id") String id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressOutputDTO> updateAddress(@PathVariable("id") String id, @Valid @RequestBody AddressInputDTO addressInputDTO) {
        return ResponseEntity.ok(addressService.updateAddress(id, addressInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") String id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}
