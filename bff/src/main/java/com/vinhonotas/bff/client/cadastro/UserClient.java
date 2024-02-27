package com.vinhonotas.bff.client.cadastro;

import com.vinhonotas.bff.configuration.FeignConfig;
import com.vinhonotas.bff.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.UserOutputDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user", url = "${cadastro-api.url}", configuration = FeignConfig.class)
public interface UserClient {

    @PostMapping("/users")
    UserOutputDTO createUser(@Valid @RequestBody UserInputDTO userInputDTO);

    @GetMapping("/users")
    List<UserOutputDTO> getAllUser();

    @GetMapping("/users/{id}")
    UserOutputDTO getUserById(@PathVariable("id") String id);

    @GetMapping("/users/name/{name}")
    UserOutputDTO getUserByName(@PathVariable("name") String name);

    @PutMapping("/users/{id}")
    UserOutputDTO updateUser(@PathVariable("id") String id, @Valid @RequestBody UserInputDTO userInputDTO);

    @DeleteMapping("/users/{id}")
    Void deleteUser(@PathVariable("id") String id);

}
