package com.vinhonotas.bff.application.services.cadastro.impl;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.client.cadastro.UserClient;
import com.vinhonotas.bff.domain.enums.EnumProfile;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.AddressInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.PersonInputDTO;
import com.vinhonotas.bff.interfaces.dtos.inputs.cadastro.UserInputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.AddressOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.PersonOutputDTO;
import com.vinhonotas.bff.interfaces.dtos.outputs.cadastro.UserOutputDTO;
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
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserClient userClient;

    private UserOutputDTO userOutputDTO;
    private UserInputDTO userInputDTO;
    @BeforeEach
    void setUp() {
        userOutputDTO = createUserOutputDTO();
        userInputDTO = createUserInputDTO();
    }

    @Test
    @DisplayName("Deve criar um usuário")
    void testCreateUser() {
        when(userClient.createUser(userInputDTO)).thenReturn(userOutputDTO);
        UserOutputDTO response = assertDoesNotThrow(() -> userService.createUser(userInputDTO));

        assertNotNull(response);
        assertEquals(userOutputDTO.getId(), response.getId());
        assertEquals(userOutputDTO.getPerson(), response.getPerson());
        assertEquals(userOutputDTO.getEnumProfile(), response.getEnumProfile());
        assertEquals(userOutputDTO.getEmail(), response.getEmail());
        assertEquals(userOutputDTO.getPassword(), response.getPassword());
        verify(userClient).createUser(userInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao criar um usuário")
    void testCreateUserThrowBadRequestException() {
        when(userClient.createUser(userInputDTO)).thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_SAVING));

        Exception exception = assertThrows(Exception.class, () -> userService.createUser(userInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_SAVING, exception.getMessage());
        verify(userClient).createUser(userInputDTO);
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuários")
    void testGetAllUser() {
        List<UserOutputDTO> users = new ArrayList<>();
        users.add(userOutputDTO);
        when(userClient.getAllUser()).thenReturn(users);

        List<UserOutputDTO> list = assertDoesNotThrow(() -> userService.getAllUser());

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(userOutputDTO.getId(), list.get(0).getId());
        assertEquals(userOutputDTO.getPerson(), list.get(0).getPerson());
        assertEquals(userOutputDTO.getEnumProfile(), list.get(0).getEnumProfile());
        assertEquals(userOutputDTO.getEmail(), list.get(0).getEmail());
        assertEquals(userOutputDTO.getPassword(), list.get(0).getPassword());
        verify(userClient).getAllUser();
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar uma lista de usuários vazia")
    void testGetAllUserThrowBadRequestException() {
        when(userClient.getAllUser()).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> userService.getAllUser());
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(userClient).getAllUser();
    }

    @Test
    @DisplayName("Deve retornar um usuário por id")
    void testGetUserById() {
        when(userClient.getUserById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(userOutputDTO);

        UserOutputDTO response = assertDoesNotThrow(() -> userService.getUserById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));

        assertNotNull(response);
        assertEquals(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), response.getId());
        assertEquals(userOutputDTO.getPerson(), response.getPerson());
        assertEquals(userOutputDTO.getEnumProfile(), response.getEnumProfile());
        assertEquals(userOutputDTO.getEmail(), response.getEmail());
        assertEquals(userOutputDTO.getPassword(), response.getPassword());
        verify(userClient).getUserById(userOutputDTO.getId().toString());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar um usuário por id")
    void testGetUserByIdThrowBadRequestException() {
        when(userClient.getUserById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> userService.getUserById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(userClient).getUserById("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
    }

    @Test
    @DisplayName("Deve retornar um usuário por nome")
    void testGetUserByName() {
        when(userClient.getUserByName("Vinicius Junior")).thenReturn(userOutputDTO);

        UserOutputDTO response = assertDoesNotThrow(() -> userService.getUserByName("Vinicius Junior"));

        assertNotNull(response);
        assertEquals(userOutputDTO.getId(), response.getId());
        assertEquals(userOutputDTO.getPerson().getName(), response.getPerson().getName());
        assertEquals(userOutputDTO.getEnumProfile(), response.getEnumProfile());
        assertEquals(userOutputDTO.getEmail(), response.getEmail());
        assertEquals(userOutputDTO.getPassword(), response.getPassword());
        verify(userClient).getUserByName("Vinicius Junior");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao retornar um usuário por nome")
    void testGetUserByNameThrowBadRequestException() {
        when(userClient.getUserByName("Vinicius Junior")).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> userService.getUserByName("Vinicius Junior"));
        assertEquals(MessagesConstants.NOT_FOUND, exception.getMessage());
        verify(userClient).getUserByName("Vinicius Junior");
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    void testUpdateUser() {
        when(userClient.updateUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", userInputDTO)).thenReturn(userOutputDTO);
        userOutputDTO.setEmail("newemail@email.com");

        UserOutputDTO response = assertDoesNotThrow(() -> userService.updateUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", userInputDTO));

        assertNotNull(response);
        assertEquals(userOutputDTO.getId(), response.getId());
        assertEquals(userOutputDTO.getPerson(), response.getPerson());
        assertEquals(userOutputDTO.getEnumProfile(), response.getEnumProfile());
        assertEquals("newemail@email.com", response.getEmail());
        assertEquals(userOutputDTO.getPassword(), response.getPassword());
        verify(userClient).updateUser(userOutputDTO.getId().toString(), userInputDTO);
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar um usuário")
    void testUpdateUserThrowBadRequestException() {
        when(userClient.updateUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", userInputDTO))
                .thenThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_UPDATING));

        Exception exception = assertThrows(Exception.class, () -> userService.updateUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", userInputDTO));
        assertEquals(MessagesConstants.ERROR_WHEN_UPDATING, exception.getMessage());
        verify(userClient).updateUser(userOutputDTO.getId().toString(), userInputDTO);
    }

    @Test
    @DisplayName("Deve deletar um usuário")
    void testDeleteUser() {
        assertDoesNotThrow(() -> userService.deleteUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        verify(userClient).deleteUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao deletar um usuário")
    void testDeleteUserThrowBadRequestException() {
        doThrow(new BadRequestException(MessagesConstants.ERROR_WHEN_DELETING))
                .when(userClient).deleteUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");

        Exception exception = assertThrows(Exception.class, () -> userService.deleteUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"));
        assertEquals(MessagesConstants.ERROR_WHEN_DELETING, exception.getMessage());
        verify(userClient).deleteUser("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
    }

    private UserInputDTO createUserInputDTO() {
        return UserInputDTO.builder()
                .person(createPersonInputDTO())
                .enumProfile(EnumProfile.OENOPHILE.getCode())
                .email("user@email.com")
                .password("123456")
                .build();
    }

    private UserOutputDTO createUserOutputDTO() {
        return UserOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .person(createPersonOutputDTO())
                .enumProfile(EnumProfile.OENOPHILE)
                .email("user@email.com")
                .password("123456")
                .build();
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

}
