package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.AromasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AromasRepository extends JpaRepository<AromasEntity, UUID> {

}
