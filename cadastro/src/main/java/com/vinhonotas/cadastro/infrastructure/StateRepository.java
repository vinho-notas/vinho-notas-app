package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StateRepository extends JpaRepository<StateEntity, UUID> {
    StateEntity findByStateName(String name);

    List<StateEntity> findByUf(String uf);
}
