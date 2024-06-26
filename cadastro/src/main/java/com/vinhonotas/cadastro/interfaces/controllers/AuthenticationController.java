package com.vinhonotas.cadastro.interfaces.controllers;

import com.vinhonotas.cadastro.application.services.PersonService;
import com.vinhonotas.cadastro.application.services.TokenService;
import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.domain.entities.exceptions.UserProfileException;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import com.vinhonotas.cadastro.infrastructure.UserRepository;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.AuthenticationDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.LoginResponseDTO;
import com.vinhonotas.cadastro.utils.EnumConverter;
import com.vinhonotas.cadastro.utils.MessagesConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "Operações relacionadas a autenticação")
public class AuthenticationController{

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final PersonService personService;
    private final TokenService tokenService;

    @Operation(summary = "Realiza o login de um usuário")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        log.info("login :: Login request received {}: ", data);
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        log.info("login :: usernamePassword {}: ",usernamePassword);
        var auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        LoginResponseDTO login = LoginResponseDTO.builder().token(token).build();
        return ResponseEntity.ok(login);
    }

    @Operation(summary = "Realiza o registro de um usuário")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserInputDTO data){
        log.info("register :: Register request received {}: ", data);
        UserDetails userDetails = repository.findByEmail(data.getEmail());
        if (Objects.nonNull(userDetails)) {
            return ResponseEntity.badRequest().build();
        }

        String encoded = new BCryptPasswordEncoder().encode(data.getPassword());
        PersonEntity person = personService.getById(UUID.fromString(data.getPersonId()));
        UserEntity user = UserEntity.builder()
                .person(person)
                .email(data.getEmail())
                .password(encoded)
                .enumProfile(verifyProfile(data.getEnumProfile()))
                .dthreg(LocalDateTime.now())
                .build();

        log.info("register :: User created {}: ", user);
        repository.save(user);

        return ResponseEntity.ok().build();
    }

    private EnumProfile verifyProfile(String enumProfile) {
        EnumProfile profile = EnumConverter.fromString(enumProfile, EnumProfile.class);
        if (!EnumProfile.OENOPHILE.equals(profile)) {
            log.error("verifyProfile :: Ocorreu um erro: {}", MessagesConstants.INVALID_PROFILE);
            throw new UserProfileException(MessagesConstants.INVALID_PROFILE);
        }
        return profile;
    }

}
