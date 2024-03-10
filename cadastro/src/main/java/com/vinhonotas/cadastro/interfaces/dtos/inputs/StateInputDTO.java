package com.vinhonotas.cadastro.interfaces.dtos.inputs;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateInputDTO {

    private String stateName;
    private String uf;
    private CountryInputDTO country;

}
