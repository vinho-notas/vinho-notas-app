CREATE TABLE IF NOT EXISTS address (
    id uuid NOT NULL,
    street varchar(500) NOT NULL,
    number int NOT NULL,
    complement varchar(255),
    district varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    state_id uuid NOT NULL,
    phone_number varchar(255) NOT NULL,
    CONSTRAINT address_pk PRIMARY KEY (id),
    CONSTRAINT address_fk FOREIGN KEY (state_id) REFERENCES state(id)
);


