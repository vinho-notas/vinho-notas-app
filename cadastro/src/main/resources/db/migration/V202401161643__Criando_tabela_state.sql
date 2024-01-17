CREATE TABLE IF NOT EXISTS cadastro.Tbstate (
     id uuid NOT NULL,
     statename varchar(255) NOT NULL,
     uf varchar(2) NOT NULL,
     country_id uuid NOT NULL,
     CONSTRAINT tbstate_pk PRIMARY KEY (id),
     CONSTRAINT tbstate_fk FOREIGN KEY (country_id) REFERENCES cadastro.Tbcountry(id)
);


