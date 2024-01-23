CREATE TABLE IF NOT EXISTS cadastro.Tbaddress (
    id uuid NOT NULL,
    addressdescription varchar(500) NOT NULL,
    addressnumber int NOT NULL,
    complement varchar(255),
    district varchar(255) NOT NULL,
    zipcode varchar(10) NOT NULL,
    city varchar(255) NOT NULL,
    state_id uuid NOT NULL,
    country_id uuid NOT NULL,
    phonenumber varchar(255) NOT NULL,
    CONSTRAINT tbaddress_pk PRIMARY KEY (id),
    CONSTRAINT tbaddress_state_fk FOREIGN KEY (state_id) REFERENCES cadastro.Tbstate(id),
    CONSTRAINT tbaddress_country_fk FOREIGN KEY (country_id) REFERENCES cadastro.Tbcountry(id)
);


