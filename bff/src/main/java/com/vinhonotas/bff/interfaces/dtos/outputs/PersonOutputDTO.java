package com.vinhonotas.bff.interfaces.dtos.outputs;

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
    private AddressOutputDTO address;

}
