package com.vinhonotas.cadastro.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EditUserInputDTO {

    private String userId;
    private String personName;
    private String enumProfile;
    private String email;
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
