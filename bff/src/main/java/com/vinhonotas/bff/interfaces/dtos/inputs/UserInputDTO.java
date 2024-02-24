package com.vinhonotas.bff.interfaces.dtos.inputs;

import com.vinhonotas.bff.domain.enums.EnumProfile;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInputDTO {

    private PersonInputDTO person;
    private EnumProfile enumProfile;
    private String email;
    private String password;

}
