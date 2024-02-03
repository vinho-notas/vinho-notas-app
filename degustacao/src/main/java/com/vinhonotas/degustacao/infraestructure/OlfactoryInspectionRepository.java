package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.OlfactoryInspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OlfactoryInspectionRepository extends JpaRepository<OlfactoryInspectionEntity, UUID> {

}
