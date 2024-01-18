package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {
    CountryEntity findByCountryName(String name);

    List<CountryEntity> findByContinentName(String continent);
}
