package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenServiceImpl;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        user = UserEntity.builder()
                .id(UUID.fromString("71c6ac9c-9a1a-4b67-9c40-393556fbe290"))
                .person(Mockito.mock(PersonEntity.class))
                .enumProfile(EnumProfile.OENOPHILE)
                .email("email@email.com")
                .password("1234")
                .build();

        ReflectionTestUtils.setField(tokenServiceImpl, "secret", "secretKey");
    }

    @Test
    void testGenerateToken() {
        String result = assertDoesNotThrow(() -> tokenServiceImpl.generateToken(user));
        assertNotNull(result);
    }

    @Test
    void testGenerateTokenWithException() {
        ReflectionTestUtils.setField(tokenServiceImpl, "secret", null);
        Exception exception = assertThrows(Exception.class, () -> tokenServiceImpl.generateToken(user));
        assertEquals("The Secret cannot be null", exception.getMessage());
    }

    @Test
    void testValidateToken() {
        String token = assertDoesNotThrow(() -> tokenServiceImpl.generateToken(user));

        String result = tokenServiceImpl.validateToken(token);
        assertNotNull(result);
        assertEquals("email@email.com", result);

    }

}
