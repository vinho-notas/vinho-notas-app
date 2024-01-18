package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByPersonName(String name);
}
