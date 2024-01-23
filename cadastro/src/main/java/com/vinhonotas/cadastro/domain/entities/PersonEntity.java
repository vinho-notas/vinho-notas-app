package com.vinhonotas.cadastro.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tbperson", schema = "cadastro")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "document")
    private String document;
    @Column(name = "birthdate")
    private LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;
}
