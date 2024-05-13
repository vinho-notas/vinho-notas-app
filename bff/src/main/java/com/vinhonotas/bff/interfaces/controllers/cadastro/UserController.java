package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.vinhonotas.bff.application.services.cadastro.UserService;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AuthenticationDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.EditUserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.UserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.LoginResponseDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.UserOutputDTO;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Cria um usuário")
    @PostMapping
    public ResponseEntity<UserOutputDTO> createUser(@Valid @RequestBody UserInputDTO userInputDTO) {
        return ResponseEntity.ok(userService.createUser(userInputDTO));
    }

    @Operation(summary = "Retorna todos os usuários")
    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @Operation(summary = "Retorna um usuário pelo seu id")
    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Retorna um usuário pelo seu nome")
    @GetMapping("/name/{name}")
    public ResponseEntity<UserOutputDTO> getUserByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @Operation(summary = "Atualiza um usuário")
    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> updateUser(@PathVariable("id") String id, @Valid @RequestBody EditUserInputDTO editUserInputDTO) {
        return ResponseEntity.ok(userService.updateUser(id, editUserInputDTO));
    }

    @Operation(summary = "Deleta um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Realiza o login de um usuário")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthenticationDTO data) {
        return ResponseEntity.ok(userService.login(data));
    }

    @Operation(summary = "Deleta uma lista de usuários")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllUser(@RequestBody List<String> ids) {
        ids.forEach(userService::deleteUser);
        return ResponseEntity.noContent().build();
    }

}
