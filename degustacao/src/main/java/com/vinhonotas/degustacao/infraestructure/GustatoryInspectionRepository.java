package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.GustatoryInspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GustatoryInspectionRepository extends JpaRepository<GustatoryInspectionEntity, UUID> {

}
