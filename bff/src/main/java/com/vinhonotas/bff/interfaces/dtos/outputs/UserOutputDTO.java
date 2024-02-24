package com.vinhonotas.bff.interfaces.dtos.outputs;

import com.vinhonotas.bff.domain.enums.EnumProfile;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserOutputDTO {

    private UUID id;
    private PersonOutputDTO person;
    private EnumProfile enumProfile;
    private String email;
    private String password;

}
