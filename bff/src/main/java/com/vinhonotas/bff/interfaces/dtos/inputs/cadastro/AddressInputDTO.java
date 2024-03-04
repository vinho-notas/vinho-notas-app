package com.vinhonotas.bff.interfaces.dtos.inputs.cadastro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressInputDTO {

    private String addressDescription;
    private int addressNumber;
    private String complement;
    private String district;
    private String zipCode;
    private String city;
    private StateInputDTO uf;
    private CountryInputDTO country;
    private String phoneNumber;

}
