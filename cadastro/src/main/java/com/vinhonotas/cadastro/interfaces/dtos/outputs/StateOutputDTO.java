package com.vinhonotas.cadastro.interfaces.dtos.outputs;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class StateOutputDTO {

    private UUID id;
    private String stateName;
    private String uf;
    private CountryEntity country;
}
