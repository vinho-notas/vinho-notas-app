package com.vinhonotas.cadastro.interfaces.dtos.outputs;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserOutputDTO {

    private UUID id;
    private PersonEntity person;
    private EnumProfile enumProfile;
    private String email;
    private String password;
}
