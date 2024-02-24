package com.vinhonotas.bff.interfaces.dtos.outputs;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class StateOutputDTO {

    private UUID id;
    private String stateName;
    private String uf;
    private CountryOutputDTO country;

}
