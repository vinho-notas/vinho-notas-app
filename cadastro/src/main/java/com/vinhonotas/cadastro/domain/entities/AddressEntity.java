package com.vinhonotas.cadastro.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Tbaddress", schema = "cadastro")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "addressdescription")
    private String addressDescription;

    @Column(name = "addressnumber")
    private int addressNumber;

    @Column(name = "complement")
    private String complement;

    @Column(name = "district")
    private String district;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity uf;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "dthreg")
    private LocalDateTime dthreg;

    @Column(name = "userreg")
    private String userreg;

    @Column(name = "dthalt")
    private LocalDateTime dthalt;

    @Column(name = "useralt")
    private String useralt;

}
