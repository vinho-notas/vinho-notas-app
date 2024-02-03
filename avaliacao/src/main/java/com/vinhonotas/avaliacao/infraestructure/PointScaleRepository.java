package com.vinhonotas.avaliacao.infraestructure;

import com.vinhonotas.avaliacao.domain.entities.PointScaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PointScaleRepository extends JpaRepository<PointScaleEntity, UUID> {

}
