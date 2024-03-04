package com.vinhonotas.bff.interfaces.dtos.inputs.cadastro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateInputDTO {

    private String stateName;
    private String uf;
    private CountryInputDTO country;

}
