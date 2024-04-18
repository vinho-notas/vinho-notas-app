package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByPersonName(String name);

    UserEntity findByPersonId(UUID id);
    UserEntity findByPersonDocument(String document);
    UserDetails findByEmail(String email);
}
