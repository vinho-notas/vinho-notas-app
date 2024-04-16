package com.vinhonotas.cadastro.application.services.impl;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import com.vinhonotas.cadastro.domain.entities.UserEntity;
import com.vinhonotas.cadastro.domain.enums.EnumProfile;
import com.vinhonotas.cadastro.infrastructure.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Log4j2
@ExtendWith(MockitoExtension.class)
class AuthorizationServiceImplTest {

    @InjectMocks
    private AuthorizationServiceImpl authorizationService;

    @Mock
    private UserRepository repository;

    @Test
    void testLoadUserByName() {
        var user = UserEntity.builder()
                .id(UUID.fromString("24690839-a007-4af7-b4fe-9e81e42b7465"))
                .person(Mockito.mock(PersonEntity.class))
                .enumProfile(EnumProfile.OENOPHILE)
                .email("email@email.com")
                .password("123456")
                .build();

        when(repository.findByEmail("email@email.com")).thenReturn(user);
        UserDetails userDetails = assertDoesNotThrow(() -> authorizationService.loadUserByUsername("email@email.com"));

        assertNotNull(userDetails);
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        userDetails.getAuthorities().forEach(authority -> assertTrue(authority.getAuthority().contains("ROLE_OENOPHILE")));
    }

    @Test
    void testLoadUserByNameWithNullUser() {
        when(repository.findByEmail("email@email.com")).thenThrow(new UsernameNotFoundException("User not found"));
        Exception ex = assertThrows(Exception.class, () -> authorizationService.loadUserByUsername("email@email.com"));
        assertEquals("User not found", ex.getMessage());
    }

}
