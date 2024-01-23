CREATE TABLE IF NOT EXISTS cadastro.Tbperson (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    document varchar(20) NOT NULL,
    birthdate date NOT NULL,
    address_id uuid NOT NULL,
    CONSTRAINT tbperson_pk PRIMARY KEY (id),
    CONSTRAINT tbperson_fk FOREIGN KEY (address_id) REFERENCES cadastro.Tbaddress(id)
);

