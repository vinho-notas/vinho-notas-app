package com.vinhonotas.cadastro.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
    private String street;
    private int number;
    private String complement;
    private String district;
    private String city;
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity uf;
    private String phoneNumber;
}
