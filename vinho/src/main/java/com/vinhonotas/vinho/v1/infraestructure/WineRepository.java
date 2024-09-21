package com.vinhonotas.vinho.v1.infraestructure;

import com.vinhonotas.vinho.v1.domain.entities.WineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WineRepository extends JpaRepository<WineEntity, UUID> {
}
