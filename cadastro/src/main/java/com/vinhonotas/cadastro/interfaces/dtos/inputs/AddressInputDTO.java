package com.vinhonotas.cadastro.interfaces.dtos.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

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
    private LocalDateTime dthreg;
    private String userreg;
    private LocalDateTime dthalt;
    private String useralt;

}
