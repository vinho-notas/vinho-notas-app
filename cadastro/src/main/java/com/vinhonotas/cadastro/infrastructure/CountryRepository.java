package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
}
