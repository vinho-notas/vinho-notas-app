package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.VisualInspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VisualInspectionRepository extends JpaRepository<VisualInspectionEntity, UUID> {

}
