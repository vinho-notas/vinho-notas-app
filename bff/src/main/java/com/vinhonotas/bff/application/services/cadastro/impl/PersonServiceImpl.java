package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.cadastro.PersonService;
import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.client.cadastro.PersonClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.PersonOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonClient personClient;

    @Override
    public PersonOutputDTO createPerson(PersonInputDTO personInputDTO) {
        log.info("createPerson :: Registrando uma pessoa com os dados: {}", personInputDTO.toString());
        try {
            return personClient.createPerson(personInputDTO);
        } catch (Exception e) {
            log.error("createPerson :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_SAVING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING);
        }
    }

    @Override
    public List<PersonOutputDTO> getAllPerson() {
        log.info("getAllPerson :: Listando todas as pessoas");
        List<PersonOutputDTO> persons = personClient.getAllPerson();
        if (persons.isEmpty()) {
            log.error("getAllPerson :: Ocorreu um erro ao listar as pessoas: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return persons;
    }

    @Override
    public PersonOutputDTO getPersonById(String id) {
        log.info("getPersonById :: Buscando pessoa pelo id: {}", id);
        PersonOutputDTO person = personClient.getPersonById(id);
        if (Objects.isNull(person)) {
            log.error("getPersonById :: Ocorreu um erro ao buscar a pessoa: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return person;
    }

    @Override
    public PersonOutputDTO getPersonByName(String name) {
        log.info("getPersonByName :: Buscando pessoa pelo nome: {}", name);
        PersonOutputDTO person = personClient.getPersonByName(name);
        if (Objects.isNull(person)) {
            log.error("getPersonByName :: Ocorreu um erro ao buscar a pessoa: {} ", MessagesConstants.NOT_FOUND);
            throw new NotFoundException(MessagesConstants.NOT_FOUND);
        }
        return person;
    }

    @Override
    public PersonOutputDTO updatePerson(String id, PersonInputDTO personInputDTO) {
        log.info("updatePerson :: Atualizando pessoa com os dados: {}", personInputDTO.toString());
        try {
            return personClient.updatePerson(id, personInputDTO);
        } catch (Exception e) {
            log.error("updatePerson :: Ocorreu um erro: {} ", MessagesConstants.ERROR_WHEN_UPDATING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING);
        }
    }

    @Override
    public void deletePerson(String id) {
        log.info("deletePerson :: Deletando pessoa pelo id: {}", id);
        try {
            personClient.deletePerson(id);
        } catch (Exception e) {
            log.error("deletePerson :: Ocorreu um erro ao deletar a pessoa: {} ", MessagesConstants.ERROR_WHEN_DELETING, e);
            throw new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING);
        }
    }

}
