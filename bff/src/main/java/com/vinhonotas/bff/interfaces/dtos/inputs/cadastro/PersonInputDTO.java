package com.vinhonotas.bff.interfaces.dtos.inputs.cadastro;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonInputDTO {

    private String name;
    private String document;
    private LocalDate birthDate;
    private AddressInputDTO address;

}
