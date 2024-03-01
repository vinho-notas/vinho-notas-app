package com.vinhonotas.bff.application.services.cadastro;

import com.vinhonotas.bff.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.PersonOutputDTO;

import java.util.List;

public interface PersonService {

    PersonOutputDTO createPerson(PersonInputDTO personInputDTO);
    List<PersonOutputDTO> getAllPerson();
    PersonOutputDTO getPersonById(String id);
    PersonOutputDTO getPersonByName(String name);
    PersonOutputDTO updatePerson(String id, PersonInputDTO personInputDTO);
    void deletePerson(String id);

}
