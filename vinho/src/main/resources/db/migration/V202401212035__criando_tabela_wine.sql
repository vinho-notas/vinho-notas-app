CREATE SCHEMA IF NOT EXISTS vinho;

CREATE TABLE IF NOT EXISTS vinho.Tbwine (
        id uuid NOT NULL,
        name varchar(255) NOT NULL,
        price numeric(19,2) NOT NULL,
        purchaselocation varchar(255) NOT NULL,
        purchasedate date NOT NULL,
        winetype varchar(255) NOT NULL,
        wineclassification varchar(255) NOT NULL,
        alcoholcontent numeric(19,2) NOT NULL,
        volume int NOT NULL,
        grape varchar(255) NOT NULL,
        winery varchar(255) NOT NULL,
        servicetemperature numeric(19,2) NOT NULL,
        harvest int NOT NULL,
        country varchar(255) NOT NULL,
        guardtime varchar(255) NOT NULL,
        region varchar(255) NOT NULL,
        maturation varchar(255) NOT NULL,
        harmonization varchar(255) NOT NULL,
        CONSTRAINT tbcountry_pk PRIMARY KEY (id)
);