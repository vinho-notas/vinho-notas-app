package com.vinhonotas.cadastro.interfaces.dtos.outputs;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import com.vinhonotas.cadastro.domain.entities.StateEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressOutputDTO {

    private String id;
    private String addressDescription;
    private int addressNumber;
    private String complement;
    private String district;
    private String zipCode;
    private String city;
    private StateEntity uf;
    private CountryEntity country;
    private String phoneNumber;
}
