package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {
    PersonEntity findByName(String name);

    PersonEntity findByDocument(String document);
}
