CREATE SCHEMA IF NOT EXISTS avaliacao;

CREATE TABLE IF NOT EXISTS avaliacao.Tbpointscale (
    id uuid NOT NULL,
    whattasted VARCHAR(500) NOT NULL,
    whentasted VARCHAR(500) NOT NULL,
    whatsaw VARCHAR(500),
    whataromas VARCHAR(500),
    whatflavors VARCHAR(500),
    whatopinion VARCHAR(500),
    pointscale VARCHAR(250),
    CONSTRAINT tbpointscale_pk PRIMARY KEY (id)
);