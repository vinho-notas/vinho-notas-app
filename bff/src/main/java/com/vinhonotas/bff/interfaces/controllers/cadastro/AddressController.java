package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.vinhonotas.bff.application.services.cadastro.AddressService;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.EditAddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ResponseEntity<AddressOutputDTO> updateAddress(@PathVariable("id") String id, @Valid @RequestBody EditAddressInputDTO editAddressInputDTO) {
        log.info("updateAddress :: Atualizando endereço com id: {}", id);
        log.info("updateAddress :: Novos dados: {}", editAddressInputDTO);
        return ResponseEntity.ok(addressService.updateAddress(id, editAddressInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") String id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllAddress(@RequestBody List<String> ids) {
        ids.forEach(addressService::deleteAddress);
        return ResponseEntity.noContent().build();
    }

}
