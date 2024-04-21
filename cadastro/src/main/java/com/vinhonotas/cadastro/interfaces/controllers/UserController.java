package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.converters.UserConverter;
import com.vinhonotas.cadastro.application.services.UserService;
import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.EditUserInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.UserOutputDTO;
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
@RequestMapping(value = "/api/v1/users")
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @Operation(summary = "Cria um usuário")
    @PostMapping
    public ResponseEntity<UserOutputDTO> createUser(@Valid @RequestBody UserInputDTO userInputDTO) {
        return ResponseEntity.ok(userConverter.convertToOutputDTO(userService.create(userInputDTO)));
    }

    @Operation(summary = "Retorna todos os usuários")
    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> getAllUser() {
        return ResponseEntity.ok(userConverter.convertToOutputDTOList(userService.getAll()));
    }

    @Operation(summary = "Retorna um usuário pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userConverter.convertToOutputDTO(userService.getById(UUID.fromString(id))));
    }

    @Operation(summary = "Retorna um usuário pelo seu nome")
    @GetMapping("/name/{name}")
    public ResponseEntity<UserOutputDTO> getUserByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(userConverter.convertToOutputDTO(userService.getByName(name)));
    }

    @Operation(summary = "Atualiza um usuário")
    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> updateUser(@PathVariable("id") String id, @Valid @RequestBody EditUserInputDTO editUserInputDTO) {
        UserEntity updated = userService.update(UUID.fromString(id), editUserInputDTO);
        return ResponseEntity.ok(userConverter.convertToOutputDTO(updated));
    }

    @Operation(summary = "Deleta um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
