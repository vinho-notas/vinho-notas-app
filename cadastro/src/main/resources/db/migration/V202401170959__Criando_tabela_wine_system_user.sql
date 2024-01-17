CREATE TYPE enum_profile AS ENUM ('OENOPHILE', 'SOMMELIER', 'PARTNER');

CREATE TABLE IF NOT EXISTS wine_system_user (
    id uuid NOT NULL,
    person_id uuid NOT NULL,
    enum_profile enum_profile NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(20) NOT NULL,
    CONSTRAINT wine_system_user_pk PRIMARY KEY (id),
    CONSTRAINT wine_system_user_fk FOREIGN KEY (person_id) REFERENCES person(id)
);

