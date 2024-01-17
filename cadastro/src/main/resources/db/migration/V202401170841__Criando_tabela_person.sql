CREATE TABLE IF NOT EXISTS person (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    document varchar(20) NOT NULL,
    birth_date date NOT NULL,
    address_id uuid NOT NULL,
    CONSTRAINT person_pk PRIMARY KEY (id),
    CONSTRAINT person_fk FOREIGN KEY (address_id) REFERENCES address(id)
);

