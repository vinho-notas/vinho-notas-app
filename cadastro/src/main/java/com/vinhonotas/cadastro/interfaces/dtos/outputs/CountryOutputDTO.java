package com.vinhonotas.cadastro.interfaces.dtos.outputs;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CountryOutputDTO {

    private UUID id;
    private String countryName;
    private String continentName;
}
