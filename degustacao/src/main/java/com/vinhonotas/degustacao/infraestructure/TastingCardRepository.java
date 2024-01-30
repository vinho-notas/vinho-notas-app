package com.vinhonotas.degustacao.infraestructure;

import com.vinhonotas.degustacao.domain.entities.TastingCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TastingCardRepository extends JpaRepository<TastingCardEntity, UUID> {

}
