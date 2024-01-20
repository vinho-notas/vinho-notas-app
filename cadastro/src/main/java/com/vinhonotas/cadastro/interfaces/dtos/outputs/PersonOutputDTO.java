package com.vinhonotas.cadastro.interfaces.dtos.outputs;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PersonOutputDTO {

    private UUID id;
    private String name;
    private String document;
    private LocalDate birthDate;
    private AddressEntity address;
}
