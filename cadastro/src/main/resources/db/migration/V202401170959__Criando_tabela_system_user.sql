CREATE TABLE IF NOT EXISTS cadastro.Tbsystemuser (
    id uuid NOT NULL,
    person_id uuid NOT NULL,
    enumprofile varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(20) NOT NULL,
    CONSTRAINT tbsystem_user_pk PRIMARY KEY (id),
    CONSTRAINT tbsystem_user_fk FOREIGN KEY (person_id) REFERENCES cadastro.Tbperson(id)
);

