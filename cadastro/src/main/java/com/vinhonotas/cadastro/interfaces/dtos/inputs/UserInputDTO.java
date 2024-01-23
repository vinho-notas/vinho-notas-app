package com.vinhonotas.cadastro.interfaces.dtos.inputs;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInputDTO {

    private PersonEntity person;
    private EnumProfile enumProfile;
    private String email;
    private String password;
}
