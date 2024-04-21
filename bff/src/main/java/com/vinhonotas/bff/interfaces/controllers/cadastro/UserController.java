package com.vinhonotas.bff.interfaces.controllers.cadastro;

import com.vinhonotas.bff.application.services.cadastro.UserService;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AuthenticationDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.EditUserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.UserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.LoginResponseDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.UserOutputDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserOutputDTO> createUser(@Valid @RequestBody UserInputDTO userInputDTO) {
        return ResponseEntity.ok(userService.createUser(userInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserOutputDTO> getUserByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> updateUser(@PathVariable("id") String id, @Valid @RequestBody EditUserInputDTO editUserInputDTO) {
        return ResponseEntity.ok(userService.updateUser(id, editUserInputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthenticationDTO data) {
        return ResponseEntity.ok(userService.login(data));
    }

}
