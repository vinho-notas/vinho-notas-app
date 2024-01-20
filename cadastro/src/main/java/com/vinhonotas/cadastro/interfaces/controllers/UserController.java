package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.UserConverter;
import com.vinhonotas.cadastro.application.services.UserService;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.UserOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping
    public ResponseEntity<UserOutputDTO> createUser(@Valid @RequestBody UserInputDTO userInputDTO) {
        return ResponseEntity.ok(userConverter.convertToOutputDTO(userService.create(userInputDTO)));
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> getAllUser() {
        return ResponseEntity.ok(userConverter.convertToOutputDTOList(userService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userConverter.convertToOutputDTO(userService.getById(UUID.fromString(id))));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserOutputDTO> getUserByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(userConverter.convertToOutputDTO(userService.getByName(name)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> updateUser(@PathVariable("id") String id, @Valid @RequestBody UserInputDTO userInputDTO) {
        return ResponseEntity.ok(
                userConverter.convertToOutputDTOUpdate(
                        userService.update(UUID.fromString(id),
                        userInputDTO),
                        UUID.fromString(id),
                        userConverter.convertToOutputDTO(
                                userService.update(UUID.fromString(id), userInputDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
