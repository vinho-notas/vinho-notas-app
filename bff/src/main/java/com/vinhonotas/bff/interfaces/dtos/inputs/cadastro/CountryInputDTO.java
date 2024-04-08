package com.vinhonotas.bff.interfaces.dtos.inputs.cadastro;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CountryInputDTO {

    private String id;
    private String countryName;
    private String continentName;
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
