CREATE TABLE IF NOT EXISTS state (
     id uuid NOT NULL,
     state varchar(255) NOT NULL,
     uf varchar(2) NOT NULL,
     country_id uuid NOT NULL,
     CONSTRAINT state_pk PRIMARY KEY (id),
     CONSTRAINT country_fk FOREIGN KEY (country_id) REFERENCES country (id)
);


