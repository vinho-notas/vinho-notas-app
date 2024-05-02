package com.vinhonotas.cadastro.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationDTO {

    private String email;
    private String password;

}
