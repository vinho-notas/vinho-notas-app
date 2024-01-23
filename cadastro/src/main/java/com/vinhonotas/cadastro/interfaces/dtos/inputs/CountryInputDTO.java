package com.vinhonotas.cadastro.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryInputDTO {

    private String countryName;
    private String continentName;
}
