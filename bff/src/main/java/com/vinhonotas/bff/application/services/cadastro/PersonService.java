package com.vinhonotas.bff.application.services.cadastro;

import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.EditPersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.PersonOutputDTO;

import java.util.List;

public interface PersonService {

    PersonOutputDTO createPerson(PersonInputDTO personInputDTO);
    List<PersonOutputDTO> getAllPerson();
    PersonOutputDTO getPersonById(String id);
    PersonOutputDTO getPersonByName(String name);
    PersonOutputDTO updatePerson(String id, EditPersonInputDTO editPersonInputDTO);
    void deletePerson(String id);

}
