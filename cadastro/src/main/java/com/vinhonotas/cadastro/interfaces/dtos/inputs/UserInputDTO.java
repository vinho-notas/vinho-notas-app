package com.vinhonotas.cadastro.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInputDTO {

    private PersonInputDTO person;
    private String enumProfile;
    private String email;
    private String password;

}
