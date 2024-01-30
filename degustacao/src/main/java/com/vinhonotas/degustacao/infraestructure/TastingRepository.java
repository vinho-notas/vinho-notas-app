package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.TastingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TastingRepository extends JpaRepository<TastingEntity, UUID>{

}
