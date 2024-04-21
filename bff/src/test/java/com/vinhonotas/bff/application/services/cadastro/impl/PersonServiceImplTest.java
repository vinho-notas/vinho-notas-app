package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.cadastro.PersonClient;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.EditPersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.PersonOutputDTO;
import com.vinhonotas.bff.utils.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonClient personClient;

    private PersonOutputDTO personOutputDTO;
    private PersonInputDTO personInputDTO;
    private EditPersonInputDTO editPersonInputDTO;

    @BeforeEach
    void setUp() {
        personOutputDTO = createPersonOutputDTO();
        personInputDTO = createPersonInputDTO();
        editPersonInputDTO = createEditPersonInputDTO();
    }

    @Test
    @DisplayName("Deve criar uma pessoa")
    void testCreatePerson() {
        when(personClient.createPerson(personInputDTO)).thenReturn(personOutputDTO);
        PersonOutputDTO response = assertDoesNotThrow(() -> personService.createPerson(personInputDTO));

        assertNotNull(response);
        assertEquals(personOutputDTO.getId(), response.getId());
        assertEquals(personOutputDTO.getName(), response.getName());
        assertEquals(personOutputDTO.getDocument(), response.getDocument());
        assertEquals(personOutputDTO.getBirthDate(), response.getBirthDate());
        assertEquals(personOutputDTO.getAddress(), response.getAddress());
        verify(personClient).createPerson(personInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar uma pessoa")
    void testCreatePersonThrowBadRequestException() {
        when(personClient.createPerson(personInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> personService.createPerson(personInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(personClient).createPerson(personInputDTO);
    }

    @Test
    @DisplayName("Deve retornar uma lista de pessoas")
    void testGetAllPerson() {
        List<PersonOutputDTO> persons = new ArrayList<>();
        persons.add(personOutputDTO);
        when(personClient.getAllPerson()).thenReturn(persons);

        List<PersonOutputDTO> list = assertDoesNotThrow(() -> personService.getAllPerson());

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(personOutputDTO.getId(), list.get(0).getId());
        assertEquals(personOutputDTO.getName(), list.get(0).getName());
        assertEquals(personOutputDTO.getDocument(), list.get(0).getDocument());
        assertEquals(personOutputDTO.getBirthDate(), list.get(0).getBirthDate());
        assertEquals(personOutputDTO.getAddress(), list.get(0).getAddress());
        verify(personClient).getAllPerson();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma lista de pessoas")
    void testGetAllPersonThrowBadRequestException() {
        when(personClient.getAllPerson()).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> personService.getAllPerson());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(personClient).getAllPerson();
    }

    @Test
    @DisplayName("Deve retornar uma pessoa por id")
    void testGetPersonById() {
        when(personClient.getPersonById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(personOutputDTO);

        PersonOutputDTO response = assertDoesNotThrow(() -> personService.getPersonById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));

        assertNotNull(response);
        assertEquals(personOutputDTO.getId(), response.getId());
        assertEquals(personOutputDTO.getName(), response.getName());
        assertEquals(personOutputDTO.getDocument(), response.getDocument());
        assertEquals(personOutputDTO.getBirthDate(), response.getBirthDate());
        assertEquals(personOutputDTO.getAddress(), response.getAddress());
        verify(personClient).getPersonById(personOutputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma pessoa por id")
    void testGetPersonByIdThrowBadRequestException() {
        Exception exception = assertThrows(Exception.class, () -> personService.getPersonById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));

        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(personClient).getPersonById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
    }

    @Test
    @DisplayName("Deve retornar uma pessoa por nome")
    void testGetPersonByName() {
        when(personClient.getPersonByName("Vinicius Junior")).thenReturn(personOutputDTO);

        PersonOutputDTO response = assertDoesNotThrow(() -> personService.getPersonByName("Vinicius Junior"));

        assertNotNull(response);
        assertEquals(personOutputDTO.getId(), response.getId());
        assertEquals(personOutputDTO.getName(), response.getName());
        assertEquals(personOutputDTO.getDocument(), response.getDocument());
        assertEquals(personOutputDTO.getBirthDate(), response.getBirthDate());
        assertEquals(personOutputDTO.getAddress(), response.getAddress());
        verify(personClient).getPersonByName(personOutputDTO.getName());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma pessoa por nome")
    void testGetPersonByNameThrowBadRequestException() {
        Exception exception = assertThrows(Exception.class, () -> personService.getPersonByName("Vinicius Junior"));

        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(personClient).getPersonByName(personOutputDTO.getName());
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa")
    void testUpdatePerson() {
        when(personClient.updatePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", editPersonInputDTO)).thenReturn(personOutputDTO);

        personOutputDTO.setName("Junior dos Santos");
        PersonOutputDTO response = assertDoesNotThrow(() -> personService.updatePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", editPersonInputDTO));

        assertNotNull(response);
        assertEquals("Junior dos Santos", response.getName());
        verify(personClient).updatePerson(personOutputDTO.getId().toString(), editPersonInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar uma pessoa")
    void testUpdatePersonThrowBadRequestException() {
        when(personClient.updatePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", editPersonInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> personService.updatePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", editPersonInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(personClient).updatePerson(personOutputDTO.getId().toString(), editPersonInputDTO);
    }

    @Test
    @DisplayName("Deve deletar uma pessoa")
    void testDeletePerson() {
        assertDoesNotThrow(() -> personService.deletePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        verify(personClient).deletePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar uma pessoa")
    void testDeletePersonThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(personClient).deletePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");

        Exception exception = assertThrows(Exception.class, () -> personService.deletePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(personClient).deletePerson("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
    }

    private PersonOutputDTO createPersonOutputDTO() {
        return PersonOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .name("Vinicius Junior")
                .document("123456789")
                .birthDate(LocalDate.of(1995, 10, 10))
                .address(Mockito.mock(AddressOutputDTO.class))
                .build();
    }

    private PersonInputDTO createPersonInputDTO() {
        return PersonInputDTO.builder()
                .name("Vinicius Junior")
                .document("123456789")
                .birthDate(LocalDate.of(1995, 10, 10))
                .address(Mockito.mock(AddressInputDTO.class))
                .build();
    }

    private EditPersonInputDTO createEditPersonInputDTO() {
        return EditPersonInputDTO.builder()
                .name("Junior dos Santos")
                .document("987654321")
                .birthDate(LocalDate.of(1990, 10, 10))
                .build();
    }

}
