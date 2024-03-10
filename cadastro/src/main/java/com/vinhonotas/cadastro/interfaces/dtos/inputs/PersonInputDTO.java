package com.vinhonotas.cadastro.interfaces.dtos.inputs;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
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
