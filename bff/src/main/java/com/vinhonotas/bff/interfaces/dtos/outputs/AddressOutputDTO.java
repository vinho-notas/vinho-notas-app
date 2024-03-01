package com.vinhonotas.bff.interfaces.dtos.outputs;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddressOutputDTO {

    private UUID id;
    private String addressDescription;
    private int addressNumber;
    private String complement;
    private String district;
    private String zipCode;
    private String city;
    private StateOutputDTO uf;
    private CountryOutputDTO country;
    private String phoneNumber;

}
