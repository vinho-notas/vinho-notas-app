package com.vinhonotas.vinho.infraestructure;

import com.vinhonotas.vinho.domain.entities.WineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WineRepository extends JpaRepository<WineEntity, UUID> {
}
