package com.vinhonotas.bff.interfaces.dtos.inputs.cadastro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationDTO {

    private String email;
    private String password;

}
