package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.PersonService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.cadastro.PersonClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.PersonOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonClient personClient;

    @Override
    public PersonOutputDTO createPerson(PersonInputDTO personInputDTO) {
        try {
            return personClient.createPerson(personInputDTO);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING_PERSON);
        }
    }

    @Override
    public List<PersonOutputDTO> getAllPerson() {
        List<PersonOutputDTO> persons = personClient.getAllPerson();
        if (persons.isEmpty()) {
            throw new BadRequestException(MessagesConstants.PERSONS_NOT_FOUND);
        }
        return persons;
    }

    @Override
    public PersonOutputDTO getPersonById(String id) {
        PersonOutputDTO person = personClient.getPersonById(id);
        if (Objects.isNull(person)) {
            throw new BadRequestException(MessagesConstants.PERSONS_NOT_FOUND);
        }
        return person;
    }

    @Override
    public PersonOutputDTO getPersonByName(String name) {
        PersonOutputDTO person = personClient.getPersonByName(name);
        if (Objects.isNull(person)) {
            throw new BadRequestException(MessagesConstants.PERSONS_NOT_FOUND);
        }
        return person;
    }

    @Override
    public PersonOutputDTO updatePerson(String id, PersonInputDTO personInputDTO) {
        try {
            return personClient.updatePerson(id, personInputDTO);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_UPDATE_PERSON_DATA);
        }
    }

    @Override
    public void deletePerson(String id) {
        try {
            personClient.deletePerson(id);
        } catch (Exception e) {
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING_PERSON);
        }
    }

}
