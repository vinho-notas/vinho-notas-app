package com.vinhonotas.cadastro.application.converters;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.PersonInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.inputs.UserInputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.PersonOutputDTO;
import com.vinhonotas.cadastro.interfaces.dtos.outputs.UserOutputDTO;
import com.vinhonotas.cadastro.utils.EnumConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Mock
    private PersonConverter personConverter;

    private UserInputDTO userInputDTO;
    private UserEntity userEntity;
    private UserOutputDTO userOutputDTO;

    @BeforeEach
    void setUp() {
        userEntity = createUserEntity();
        userInputDTO = createUserInputDTO();
        userOutputDTO = createUserOutputDTO();
    }

    @Test
    @DisplayName("Teste de conversão de UserInputDTO para UserEntity")
    void testToEntity() {
        UserEntity user = assertDoesNotThrow(()-> userConverter.convertToEntity(userInputDTO));
        assertNotNull(user);
        assertEquals(personConverter.convertToEntity(userInputDTO.getPerson()), user.getPerson());
        assertEquals(userInputDTO.getEnumProfile(), EnumConverter.toString(user.getEnumProfile()));
        assertEquals(userInputDTO.getEmail(), user.getEmail());
        assertEquals(userInputDTO.getPassword(), user.getPassword());
    }

    @Test
    @DisplayName("Teste de conversão para UserEntityUpdate ")
    void testToEntityUpdate() {
        userInputDTO.setEmail("update@email.com");

        UserEntity entity = assertDoesNotThrow(() -> userConverter.converteToEntityUpdate(userEntity, userEntity.getId(), userInputDTO));
        assertNotNull(userEntity);
        assertEquals(personConverter.convertToEntity(userInputDTO.getPerson()), entity.getPerson());
        assertEquals(userInputDTO.getEnumProfile(), EnumConverter.toString(entity.getEnumProfile()));
        assertEquals(userInputDTO.getEmail(), entity.getEmail());
        assertEquals(userInputDTO.getPassword(), entity.getPassword());
    }

    @Test
    @DisplayName("Teste de conversão de UserEntity para UserOutputDTO")
    void testConvertToOutputDTO() {
        UserOutputDTO userOutput = assertDoesNotThrow(() -> userConverter.convertToOutputDTO(userEntity));
        assertNotNull(userOutput);
        assertEquals(userEntity.getId(), userOutput.getId());
        assertEquals(personConverter.convertToOutputDTO(userEntity.getPerson()), userOutput.getPerson());
        assertEquals(EnumConverter.toString(userEntity.getEnumProfile()), userOutput.getEnumProfile());
        assertEquals(userEntity.getEmail(), userOutput.getEmail());
        assertEquals(userEntity.getPassword(), userOutput.getPassword());
    }

    @Test
    @DisplayName("Teste de conversão de List<UserEntity> para List<UserOutputDTO>")
    void testConvertListToOutputDTO() {
        List<UserOutputDTO> userOutput = assertDoesNotThrow(() -> userConverter.convertToOutputDTOList(List.of(userEntity)));
        assertNotNull(userOutput);
        assertEquals(userEntity.getId(), userOutput.get(0).getId());
        assertEquals(personConverter.convertToOutputDTO(userEntity.getPerson()), userOutput.get(0).getPerson());
        assertEquals(EnumConverter.toString(userEntity.getEnumProfile()), userOutput.get(0).getEnumProfile());
        assertEquals(userEntity.getEmail(), userOutput.get(0).getEmail());
        assertEquals(userEntity.getPassword(), userOutput.get(0).getPassword());
    }

    @Test
    @DisplayName("Teste de conversão para UserOutputDTOUpdate")
    void testConvertToOutputDTOUpdate() {
        when(personConverter.convertToOutputDTO(userEntity.getPerson())).thenReturn(Mockito.mock(PersonOutputDTO.class));
        userEntity.setPassword("456789");

        UserOutputDTO userOutput = assertDoesNotThrow(() -> userConverter.convertToOutputDTOUpdate(userEntity, userEntity.getId(), userOutputDTO));
        assertNotNull(userOutput);
        assertEquals(userEntity.getId(), userOutput.getId());
        assertEquals(personConverter.convertToOutputDTO(userEntity.getPerson()), userOutput.getPerson());
        assertEquals(EnumConverter.toString(userEntity.getEnumProfile()), userOutput.getEnumProfile());
        assertEquals(userEntity.getEmail(), userOutput.getEmail());
        assertEquals("456789", userOutput.getPassword());
    }

    private UserEntity createUserEntity() {
        return UserEntity.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .person(Mockito.mock(PersonEntity.class))
                .enumProfile(EnumProfile.OENOPHILE)
                .email("user@email.com")
                .password("123456")
                .build();
    }

    private UserInputDTO createUserInputDTO() {
        return UserInputDTO.builder()
                .person(Mockito.mock(PersonInputDTO.class))
                .enumProfile(EnumProfile.OENOPHILE.getCode())
                .email("user@email.com")
                .password("123456")
                .build();
    }

    private UserOutputDTO createUserOutputDTO() {
        return UserOutputDTO.builder()
                .id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"))
                .person(Mockito.mock(PersonOutputDTO.class))
                .enumProfile(EnumProfile.OENOPHILE.getCode())
                .email("user@email.com")
                .password("123456")
                .build();
    }

}