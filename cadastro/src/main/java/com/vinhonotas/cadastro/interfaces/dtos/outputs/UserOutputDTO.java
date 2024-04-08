package com.vinhonotas.cadastro.interfaces.dtos.outputs;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserOutputDTO {

    private UUID id;
    private PersonOutputDTO person;
    private String enumProfile;
    private String email;
    private String password;

}
