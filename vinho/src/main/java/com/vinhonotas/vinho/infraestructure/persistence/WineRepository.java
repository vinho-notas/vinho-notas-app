package com.vinhonotas.vinho.infraestructure.persistence;

import com.vinhonotas.vinho.infraestructure.gateways.entities.WineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WineRepository extends JpaRepository<WineEntity, UUID> {

}
