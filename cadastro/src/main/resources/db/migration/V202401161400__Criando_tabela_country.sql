CREATE SCHEMA IF NOT EXISTS cadastro;

CREATE TABLE IF NOT EXISTS cadastro.Tbcountry (
     id uuid NOT NULL,
     countryname varchar(255) NOT NULL,
     continentname varchar(255) NOT NULL,
     CONSTRAINT tbcountry_pk PRIMARY KEY (id)
);


