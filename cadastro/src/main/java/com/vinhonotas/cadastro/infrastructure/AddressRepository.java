package com.vinhonotas.cadastro.infrastructure;

import com.vinhonotas.cadastro.domain.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
